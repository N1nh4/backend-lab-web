package com.example.lab_web.Service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import com.example.lab_web.Model.ListaEmailsNotificacao;

@Service
public class EmailService {

    private JavaMailSender jms;
    
    public EmailService(JavaMailSender jms) {
        this.jms = jms;
    }

    public void enviarEmail(List<ListaEmailsNotificacao> emails) {
        for (ListaEmailsNotificacao email : emails) {
            SimpleMailMessage mensagem = new SimpleMailMessage();
            mensagem.setTo(email.getEmails().get(0));
            mensagem.setSubject("Aviso de unidade vazia!");
            mensagem.setText("Olá! Você está recebendo este email pois a unidade X esta vazia!!\n\n");
            mensagem.setFrom("rafaelnargolo@gmail.com");
            
            jms.send(mensagem);
        }
    }
}
