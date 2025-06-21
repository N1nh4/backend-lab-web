package com.example.lab_web.Model;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Atualizacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    private Status status;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Usuario usuario;
    
    public Atualizacao() {}

    public Atualizacao(Usuario usuario, Status status) {
        this.dataHora = LocalDateTime.now();
        this.usuario = usuario;
        this.status = status;
    }



    public void imprimirAtualizacao() {
        System.out.println("Data e Hora: " + dataHora);
        System.out.println("Usuario: " + usuario.getNome());
        System.out.println("Status: " + status);
    }

}
