package com.example.lab_web.Service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private JavaMailSender jms;
    
    public EmailService(JavaMailSender jms) {
        this.jms = jms;
    }

    public void enviarEmail(String email, String nome) {
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setTo(email);
        mensagem.setSubject("Bem-vindo ao nosso sistema!");
        mensagem.setText("Olá " + nome + ",\n\nSua conta foi criada com sucesso!\n\nObrigado!");
        mensagem.setFrom("rafaelnargolo@gmail.com");

        jms.send(mensagem);
    }
}
