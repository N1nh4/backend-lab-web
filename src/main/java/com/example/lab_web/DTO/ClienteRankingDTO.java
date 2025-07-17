package com.example.lab_web.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteRankingDTO {
    private String nome;
    private int contribuicoes;

    public ClienteRankingDTO(String nome, int contribuicoes) {
        this.nome = nome;
        this.contribuicoes = contribuicoes;
    }
}
