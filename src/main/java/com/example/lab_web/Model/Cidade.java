package com.example.lab_web.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Cidade {
    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "estado_id", referencedColumnName = "id", nullable = false)
    private Estado estado;

    public Cidade() {}
    
    public Cidade(String nome, Estado estado) {
        this.nome = nome;
        this.estado = estado;
    }
}
