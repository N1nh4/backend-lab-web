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
import jakarta.persistence.ManyToOne; // Alterado para ManyToOne se um Usuario pode ter muitas Atualizacoes
import jakarta.persistence.OneToOne;

@Entity
public class Atualizacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    private Status status;
    
    @Enumerated(EnumType.STRING)
    private NivelConfiabilidade confiabilidade; // Novo atributo

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Usuario usuario;

    public Atualizacao() {
        this.dataHora = LocalDateTime.now(); // Inicializa a data/hora ao criar
    }

    public Atualizacao(Usuario usuario, Status status, NivelConfiabilidade confiabilidade) {
        this.dataHora = LocalDateTime.now();
        this.usuario = usuario;
        this.status = status;
        this.confiabilidade = confiabilidade;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public NivelConfiabilidade getConfiabilidade() { return confiabilidade; }
    public void setConfiabilidade(NivelConfiabilidade confiabilidade) { this.confiabilidade = confiabilidade; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public void imprimirAtualizacao() {
        System.out.println("Data e Hora: " + dataHora);
        System.out.println("Usuario: " + (usuario != null ? usuario.getNome() : "N/A"));
        System.out.println("Status: " + status);
        System.out.println("Confiabilidade: " + confiabilidade);
    }
}