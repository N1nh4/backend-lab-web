package com.example.lab_web.DTO;

import java.util.List;

import com.example.lab_web.Model.Status;
import com.example.lab_web.Model.Unidade;

public class UnidadeDTO {
    private Unidade unidade;
    private Status statusAtual;
    private List<String> comentarios;
    private int ranking;

    public UnidadeDTO(Unidade unidade, Status statusAtual, List<String> comentarios, int ranking) {
        this.unidade = unidade;
        this.statusAtual = statusAtual;
        this.comentarios = comentarios;
        this.ranking = ranking;
    }
}
