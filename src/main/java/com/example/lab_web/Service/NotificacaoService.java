package com.example.lab_web.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(NotificacaoService.class);

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
            log.info("Assinatura atualizada: {} já acompanhava a unidade {}", email, unidadeId);
            return;
        }

        InscricaoNotificacao nova = new InscricaoNotificacao(email, unidadeId, unidade.getStatus());
        inscricaoRepository.save(nova);
        log.info("Assinatura criada: {} passou a acompanhar a unidade {} (status atual: {})",
                email, unidadeId, unidade.getStatus());
    }

    public void cancelar(String email, Long unidadeId) {
        inscricaoRepository.deleteByEmailAndUnidadeId(email, unidadeId);
        log.info("Assinatura removida: {} deixou de acompanhar a unidade {}", email, unidadeId);
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

        int enviados = 0;
        for (InscricaoNotificacao inscricao : inscricoes) {
            Status statusAssinatura = inscricao.getStatusAssinatura();
            if (statusAssinatura == statusAtual) {
                continue;
            }

            try {
                emailService.enviarEmail(inscricao.getEmail(), nomeUnidade, statusAssinatura, statusAtual);
                inscricao.setStatusAssinatura(statusAtual);
                inscricaoRepository.save(inscricao);
                enviados++;
                log.info("Notificado {} sobre mudança da unidade '{}' ({} -> {})",
                        inscricao.getEmail(), nomeUnidade, statusAssinatura, statusAtual);
            } catch (Exception e) {
                log.error("Falha ao enviar email para {} (unidade '{}'): {}",
                        inscricao.getEmail(), nomeUnidade, e.getMessage(), e);
            }
        }

        if (enviados > 0) {
            log.info("Unidade '{}' mudou de status; {} email(s) enviado(s).", nomeUnidade, enviados);
        }
    }

    public void enviarEmailTeste(String email) {
        emailService.enviarEmail(email, "TESTE", Status.VAZIO, Status.MODERADO);
    }
}
