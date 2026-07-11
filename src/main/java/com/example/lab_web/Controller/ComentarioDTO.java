package com.example.lab_web.Controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentarioDTO {
    private Long clienteId;
    private String texto;

    public ComentarioDTO() {}

    public ComentarioDTO(Long clienteId, String texto) {
        this.clienteId = clienteId;
        this.texto = texto;
    }
}
