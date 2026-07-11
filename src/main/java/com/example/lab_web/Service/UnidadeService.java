package com.example.lab_web.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.lab_web.DTO.UnidadeDTO;
import com.example.lab_web.DTO.UnidadePaginaDTO;
import com.example.lab_web.Model.Atualizacao;
import com.example.lab_web.Model.Avaliacao;
import com.example.lab_web.Model.HistoricoDeAtualizacao;
import com.example.lab_web.Model.InformacoesUnidade;
import com.example.lab_web.Model.Status;
import com.example.lab_web.Model.Unidade;
import com.example.lab_web.Repository.AtualizacaoRepository;
import com.example.lab_web.Repository.AvaliacaoRepository;
import com.example.lab_web.Repository.HistoricoDeAtualizacaoRepository;
import com.example.lab_web.Repository.InformacoesUnidadeRepository;
import com.example.lab_web.Repository.UnidadeRepository;

@Service
public class UnidadeService {

    
    private final UnidadeRepository unidadeRepository;
    private final InformacoesUnidadeRepository informacoesUnidadeRepository;
    private final HistoricoDeAtualizacaoRepository historicoDeAtualizacaoRepository;
    private final AvaliacaoRepository avaliacaoRepository;
    private final AtualizacaoRepository atualizacaoRepository;
    
    
    public UnidadeService(UnidadeRepository unidadeRepository, InformacoesUnidadeRepository informacoesUnidadeRepository, HistoricoDeAtualizacaoRepository historicoDeAtualizacaoRepository, AvaliacaoRepository avaliacaoRepository, AtualizacaoRepository atualizacaoRepository) {
        this.unidadeRepository = unidadeRepository;
        this.informacoesUnidadeRepository = informacoesUnidadeRepository;
        this.historicoDeAtualizacaoRepository = historicoDeAtualizacaoRepository;
        this.avaliacaoRepository = avaliacaoRepository;
        this.atualizacaoRepository = atualizacaoRepository;
    }
    
    public void gerarStatusAtual(Long unidadeId) {
        Unidade unidade = unidadeRepository.findById(unidadeId).orElseThrow(() -> new RuntimeException("Unidade não encontrada"));

        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime limite = agora.minusMinutes(120);

        List<Atualizacao> atualizacoesRecentes = atualizacaoRepository.findByUnidadeIdAndDataHoraAfter(unidadeId, limite);

        if (atualizacoesRecentes.isEmpty()) {
            unidade.setStatus(Status.SEM_INFORMACAO);
            unidadeRepository.save(unidade);
            return;
        }

        int somaPonderada = 0;
        int somaPesos = 0;

        for (Atualizacao a : atualizacoesRecentes) {
            int peso = getPesoPorConfiabilidade(a.getConfiabilidade());
            int valorStatus = Status.toValorNumerico(a.getStatus());

            somaPonderada += valorStatus * peso;
            somaPesos += peso;
        }

        int valorMedio = Math.round((float) somaPonderada / somaPesos);

        valorMedio = Math.min(5, Math.max(1, valorMedio));

        Status novoStatus = Status.fromValorNumerico(valorMedio);
        unidade.setStatus(novoStatus);

        unidadeRepository.save(unidade);

        System.out.println("VALOR MEDIA: " + valorMedio);

    }

    private int getPesoPorConfiabilidade(int confiabilidade) {
        if (confiabilidade == 2) {
            return 2;
        }
        return 1;
    }

    @Transactional(readOnly = true)
    public UnidadePaginaDTO getUnidade(Long id) {
        Unidade unidade = unidadeRepository.findById(id).orElse(null);
        InformacoesUnidade informacoesUnidade = informacoesUnidadeRepository.buscarInformacoesUnidadePorIdUnidade(id);
        return new UnidadePaginaDTO(unidade, informacoesUnidade);
    }

    @Transactional(readOnly = true)
    public List<UnidadeDTO> getUnidades() {
        List<Unidade> unidades = unidadeRepository.findAllWithHistorico();
        List<InformacoesUnidade> todasInfos = informacoesUnidadeRepository.buscarTodasComEndereco();

        Map<Long, InformacoesUnidade> mapaInfos = todasInfos.stream()
            .collect(Collectors.toMap(info -> info.getUnidade().getId(), info -> info));

        return unidades.stream().map(unidade -> {
            InformacoesUnidade informacoesUnidade = mapaInfos.get(unidade.getId());

            Atualizacao ultimaAtualizacao = null;
            HistoricoDeAtualizacao historicoAtualizacao = unidade.getHistoricoDeAtualizacao();

            if (historicoAtualizacao != null && historicoAtualizacao.getAtualizacoes() != null && !historicoAtualizacao.getAtualizacoes().isEmpty()) {
                ultimaAtualizacao = historicoAtualizacao.getAtualizacoes().get(0);
            }

            int n = informacoesUnidade != null ? informacoesUnidade.getNota() : 0;

            return new UnidadeDTO(unidade, informacoesUnidade, ultimaAtualizacao, n);
        }).collect(Collectors.toList());
    }
}
