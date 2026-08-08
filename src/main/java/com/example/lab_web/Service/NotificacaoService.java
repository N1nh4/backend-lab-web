package com.example.lab_web.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.lab_web.Model.InformacoesUnidade;
import com.example.lab_web.Model.InscricaoNotificacao;
import com.example.lab_web.Model.Status;
import com.example.lab_web.Model.Unidade;
import com.example.lab_web.Repository.InformacoesUnidadeRepository;
import com.example.lab_web.Repository.InscricaoNotificacaoRepository;
import com.example.lab_web.Repository.UnidadeRepository;

@Service
public class NotificacaoService {

    private final InscricaoNotificacaoRepository inscricaoRepository;
    private final UnidadeRepository unidadeRepository;
    private final InformacoesUnidadeRepository informacoesUnidadeRepository;
    private final EmailService emailService;

    public NotificacaoService(InscricaoNotificacaoRepository inscricaoRepository,
                              UnidadeRepository unidadeRepository,
                              InformacoesUnidadeRepository informacoesUnidadeRepository,
                              EmailService emailService) {
        this.inscricaoRepository = inscricaoRepository;
        this.unidadeRepository = unidadeRepository;
        this.informacoesUnidadeRepository = informacoesUnidadeRepository;
        this.emailService = emailService;
    }

    public void assinar(String email, Long unidadeId) {
        Unidade unidade = unidadeRepository.findById(unidadeId)
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada"));

        Optional<InscricaoNotificacao> existente = inscricaoRepository.findByEmailAndUnidadeId(email, unidadeId);
        if (existente.isPresent()) {
            existente.get().setStatusAssinatura(unidade.getStatus());
            inscricaoRepository.save(existente.get());
            return;
        }

        InscricaoNotificacao nova = new InscricaoNotificacao(email, unidadeId, unidade.getStatus());
        inscricaoRepository.save(nova);
    }

    public void cancelar(String email, Long unidadeId) {
        inscricaoRepository.deleteByEmailAndUnidadeId(email, unidadeId);
    }

    public List<Long> listarUnidadesInscritas(String email) {
        return inscricaoRepository.findByEmail(email).stream()
                .map(InscricaoNotificacao::getUnidadeId)
                .collect(Collectors.toList());
    }

    public void verificarMudancas(Long unidadeId) {
        Optional<Unidade> unidadeOpt = unidadeRepository.findById(unidadeId);
        if (unidadeOpt.isEmpty()) {
            return;
        }

        Status statusAtual = unidadeOpt.get().getStatus();
        List<InscricaoNotificacao> inscricoes = inscricaoRepository.findByUnidadeId(unidadeId);
        if (inscricoes.isEmpty()) {
            return;
        }

        InformacoesUnidade infos = informacoesUnidadeRepository.buscarInformacoesUnidadePorIdUnidade(unidadeId);
        String nomeUnidade = infos != null ? infos.getNome() : "Unidade " + unidadeId;

        for (InscricaoNotificacao inscricao : inscricoes) {
            Status statusAssinatura = inscricao.getStatusAssinatura();
            if (statusAssinatura == statusAtual) {
                continue;
            }

            try {
                emailService.enviarEmail(inscricao.getEmail(), nomeUnidade, statusAssinatura, statusAtual);
            } catch (Exception e) {
                System.err.println("Erro ao enviar email (não crítico): " + e.getMessage());
            }

            inscricao.setStatusAssinatura(statusAtual);
            inscricaoRepository.save(inscricao);
        }
    }
}
