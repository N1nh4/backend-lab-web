package com.example.lab_web.Observer;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Evento extends ApplicationEvent {
    private final String email;
    private final String nome;

    public Evento(Object source, String email, String nome) {
        super(source);
        this.email = email;
        this.nome = nome;
    }
}
