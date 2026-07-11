package com.example.lab_web.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteRankingDTO {
    private String nome;
    private int contribuicoes;
    private String fotoURL;

    public ClienteRankingDTO(String nome, int contribuicoes, String fotoURL) {
        this.nome = nome;
        this.contribuicoes = contribuicoes;
        this.fotoURL = fotoURL;
    }
}
