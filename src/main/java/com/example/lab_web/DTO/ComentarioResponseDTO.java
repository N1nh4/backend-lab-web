package com.example.lab_web.DTO;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentarioResponseDTO {
    private Long id;
    private String texto;
    private LocalDateTime data_hora;
    private String clienteNome;
    private String clienteFotoURL;
    private Long clienteId;

    public ComentarioResponseDTO(Long id, String texto, LocalDateTime data_hora, String clienteNome, String clienteFotoURL, Long clienteId) {
        this.id = id;
        this.texto = texto;
        this.data_hora = data_hora;
        this.clienteNome = clienteNome;
        this.clienteFotoURL = clienteFotoURL;
        this.clienteId = clienteId;
    }
}
