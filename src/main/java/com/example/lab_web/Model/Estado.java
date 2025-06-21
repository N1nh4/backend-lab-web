package com.example.lab_web.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Estado {
    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    public Estado() {}
    
    public Estado(String nome) {
        this.nome = nome;
    }
}
