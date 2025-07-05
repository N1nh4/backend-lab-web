package com.example.lab_web.Model;

import java.time.LocalDateTime; // Adicionado para o método imprimirAtualizacao se for mantido

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Contribuicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHora; // Adicionado para alinhar com o método imprimirAtualizacao

    @Enumerated(EnumType.STRING)
    private Status status;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    
    public Contribuicao() {
        this.dataHora = LocalDateTime.now(); // Inicializa a data/hora ao criar
    }

    public Contribuicao(Status status, Cliente cliente) {
        this.dataHora = LocalDateTime.now();
        this.status = status;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void VisualizarContribuicao() {
        System.out.println("Status: " + status);
    }

    public void registrarStatus() {
        System.out.println("Status registrado com sucesso!");
    }

    // Método renomeado para 'imprimirDetalhes' ou similar, pois 'imprimirAtualizacao' está em Atualizacao
    public void imprimirDetalhesContribuicao() {
        System.out.println("Data e Hora da Contribuição: " + dataHora);
        if (cliente != null) {
            System.out.println("Cliente: " + cliente.getNome());
        } else {
            System.out.println("Cliente: Não associado");
        }
        System.out.println("Status: " + status);
    }
}