package com.example.lab_web.Service;

import org.springframework.boot.ApplicationArguments;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.example.lab_web.Observer.Evento;

@Service
public class EmailService {
    private ApplicationEventPublisher aep;
    
    public EmailService(ApplicationEventPublisher aep) {
        this.aep = aep;
    }

    public void enviarEmail(String email, String nome) {
        Evento evento = new Evento(this, email, nome);
        aep.publishEvent(evento);
    }
}
