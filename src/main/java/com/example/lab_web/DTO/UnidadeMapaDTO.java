package com.example.lab_web.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnidadeMapaDTO {
    private String nome;
    private double lat;
    private double lng;
    private String status;

    public UnidadeMapaDTO(String nome, double lat, double lng, String status) {
        this.nome = nome;
        this.lat = lat;
        this.lng = lng;
        this.status = status;
    }
}
