package com.example.lab_web.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class InscricaoNotificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private Long unidadeId;

    @Enumerated(EnumType.STRING)
    private Status statusAssinatura;

    public InscricaoNotificacao() {}

    public InscricaoNotificacao(String email, Long unidadeId, Status statusAssinatura) {
        this.email = email;
        this.unidadeId = unidadeId;
        this.statusAssinatura = statusAssinatura;
    }
}
