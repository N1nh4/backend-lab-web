package com.example.lab_web.Controller;

import com.example.lab_web.Model.Atualizacao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizacaoDTO {
    private int idUsuario;
    private String nome;
    private String status;
    private String dataHora;

    public AtualizacaoDTO(int idUsuario, String nome, String status, String dataHora) {
        this.nome = nome;
        this.idUsuario = idUsuario;
        this.status = status;
        this.dataHora = dataHora;
    }
}