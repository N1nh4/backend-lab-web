package com.example.lab_web.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum Status {
    SEM_INFORMACAO,
    VAZIO,
    POUCO_VAZIO,
    MODERADO,
    CHEIO,
    MUITO_CHEIO;

    public static int toValorNumerico(Status status) {
        return switch (status) {
            case SEM_INFORMACAO -> 0;
            case VAZIO -> 1;
            case POUCO_VAZIO -> 2;
            case MODERADO -> 3;
            case CHEIO -> 4;
            case MUITO_CHEIO -> 5;
        };
    }

    public static Status fromValorNumerico(int valor) {
        return switch (valor) {
            case 1 -> VAZIO;
            case 2 -> POUCO_VAZIO;
            case 3 -> MODERADO;
            case 4 -> CHEIO;
            case 5 -> MUITO_CHEIO;
            default -> SEM_INFORMACAO;
        };
    }
}

