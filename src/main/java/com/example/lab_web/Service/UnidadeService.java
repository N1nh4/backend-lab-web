package com.example.lab_web.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        valorMedio = Math.min(4, Math.max(1, valorMedio));

        Status novoStatus = Status.fromValorNumerico(valorMedio);
        unidade.setStatus(novoStatus);

        unidadeRepository.save(unidade);

    }

    private int getPesoPorConfiabilidade(int confiabilidade) {
        if (confiabilidade == 2) {
            return 2;
        }
        return 1;
    }
    public UnidadePaginaDTO getUnidade(Long id) {
        Unidade unidade = unidadeRepository.findById(id).orElse(null);
        InformacoesUnidade informacoesUnidade = informacoesUnidadeRepository.buscarInformacoesUnidadePorIdUnidade(id);
        return new UnidadePaginaDTO(unidade, informacoesUnidade);
    }

     public List<UnidadeDTO> getUnidades() {
        List<Unidade> unidades = unidadeRepository.findAll();

        return unidades.stream().map(unidade -> {
            // 1. Buscar InformacoesUnidade relacionada à Unidade
            // Assumindo que você tem um método para buscar InformacoesUnidade por id_unidade
            Optional<InformacoesUnidade> informacoesUnidadeOpt = informacoesUnidadeRepository.findById(unidade.getId());
            InformacoesUnidade informacoesUnidade = informacoesUnidadeOpt.orElse(null); // Tratar caso não encontre

            Atualizacao ultimaAtualizacao = null;
            HistoricoDeAtualizacao historicoAtualizacao = unidade.getHistoricoDeAtualizacao(); // Obtenha o histórico diretamente da Unidade

            if (historicoAtualizacao != null && historicoAtualizacao.getAtualizacoes() != null && !historicoAtualizacao.getAtualizacoes().isEmpty()) {
                // Se a anotação @OrderBy("dataHora DESC") já estiver na entidade HistoricoDeAtualizacao.atualizacoes,
                // a primeira atualização na lista já será a mais recente.
                ultimaAtualizacao = historicoAtualizacao.getAtualizacoes().get(0);
            }

            int n = informacoesUnidadeRepository.getNota(unidade.getId());

            // Crie e retorne o DTO
            return new UnidadeDTO(unidade, informacoesUnidade, ultimaAtualizacao, n);
        }).collect(Collectors.toList());
    }
}
