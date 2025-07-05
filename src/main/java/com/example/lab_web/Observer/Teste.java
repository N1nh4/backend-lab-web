package com.example.lab_web.Observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class Teste {
    
    @Autowired
    private JavaMailSender jms;

    @EventListener
    public void on(Evento e) {
        String email = e.getEmail();
        String nome = e.getNome();
        
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setTo(email);
        mensagem.setText("Seja bem vindo, " + nome);
        mensagem.setSubject("Ola, " + nome + "! Seja bem vindo ao nosso site!");
        mensagem.setFrom("rafaelnargolo@gmail.com");
    }
}
