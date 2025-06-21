package com.example.lab_web.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Contribuicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int contribuicoes;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Contribuicao() {}

    public Contribuicao(Status status) {
        this.status = status;
        this.contribuicoes = 0;
    }

    public int getContribuicoes() {
        return contribuicoes;
    }

    public void adicionarContribuicao() {
        this.contribuicoes++;
    }

    public void VisualizarContribuicao() {
        System.out.println("Total de Contribuições: " + contribuicoes);
        System.out.println("Status: " + status);
    }

    public void registrarStatus() {
        System.out.println("Status registrado com sucesso!");
    }

}
