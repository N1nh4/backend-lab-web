package com.example.lab_web.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.lab_web.Model.Status;

@Service
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    private JavaMailSender jms;

    @Value("${spring.mail.username:comunica.saudeagora@gmail.com}")
    private String remetente;

    public EmailService(JavaMailSender jms) {
        this.jms = jms;
    }

    public void enviarEmail(String emailDestino, String nomeUnidade, Status statusAnterior, Status statusNovo) {
        log.info("Enviando email de '{}' para '{}' sobre a unidade '{}' ({} -> {})",
                remetente, emailDestino, nomeUnidade, statusAnterior, statusNovo);

        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setTo(emailDestino);
        mensagem.setFrom(remetente);
        mensagem.setSubject("Saúde Agora - Mudança de lotação em " + nomeUnidade);
        mensagem.setText("Olá!\n\n"
                + "Você ativou as notificações da unidade " + nomeUnidade + " e o status de lotação dela mudou.\n\n"
                + "Status anterior: " + formatarStatus(statusAnterior) + "\n"
                + "Status atual: " + formatarStatus(statusNovo) + "\n\n"
                + "A unidade " + descreverMudanca(statusAnterior, statusNovo) + ".\n\n"
                + "Acesse o Saúde Agora para conferir mais detalhes.");

        jms.send(mensagem);
        log.info("Email enviado com sucesso para {}", emailDestino);
    }

    private String descreverMudanca(Status statusAnterior, Status statusNovo) {
        int valorAnterior = Status.toValorNumerico(statusAnterior);
        int valorNovo = Status.toValorNumerico(statusNovo);

        if (valorNovo < valorAnterior) {
            return "está mais vazia agora";
        }
        if (valorNovo > valorAnterior) {
            return "está mais cheia agora";
        }
        return "mudou de status";
    }

    private String formatarStatus(Status status) {
        if (status == null) {
            return "Sem informação";
        }
        return status.toString().replace('_', ' ').toLowerCase();
    }
}
