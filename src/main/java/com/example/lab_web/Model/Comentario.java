package com.example.lab_web.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "unidade_id", referencedColumnName = "id", nullable = false)
    private Unidade unidade;

    private String texto;

    private LocalDateTime data_hora;

    public Comentario() {}

    public Comentario(Cliente cliente, Unidade unidade, String texto, LocalDateTime data_hora) {
        this.cliente = cliente;
        this.unidade = unidade;
        this.texto = texto;
        this.data_hora = LocalDateTime.now();
    }

}
