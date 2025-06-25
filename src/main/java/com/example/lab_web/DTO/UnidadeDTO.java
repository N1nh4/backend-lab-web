package com.example.lab_web.DTO;

import java.util.List;

import com.example.lab_web.Model.Status;
import com.example.lab_web.Model.Unidade;

public class UnidadeDTO {
    private Unidade unidade;
    private Status statusAtual;
    private List<String> comentarios;

    public UnidadeDTO(Unidade unidade, Status statusAtual, List<String> comentarios) {
        this.unidade = unidade;
        this.statusAtual = statusAtual;
        this.comentarios = comentarios;
    }
}
