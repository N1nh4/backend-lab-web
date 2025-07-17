package com.example.lab_web.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDTO {
    private String email;
    private Long idUnidade;

    public EmailDTO(String email, Long idUnidade) {
        this.email = email;
        this.idUnidade = idUnidade;
    }
}