package com.example.lab_web.DTO;

import com.example.lab_web.Model.Atualizacao;
import com.example.lab_web.Model.Status;

public class AtualizacaoResponseDTO {
    private Atualizacao atualizacao;
    private Status statusUnidadeAtualizado;

    public AtualizacaoResponseDTO(Atualizacao atualizacao, Status statusUnidadeAtualizado) {
        this.atualizacao = atualizacao;
        this.statusUnidadeAtualizado = statusUnidadeAtualizado;
    }

    public Atualizacao getAtualizacao() {
        return atualizacao;
    }

    public Status getStatusUnidadeAtualizado() {
        return statusUnidadeAtualizado;
    }
}
