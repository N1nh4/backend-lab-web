package com.example.lab_web.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"cliente_id", "informacoes_unidade_id"}))
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int nota;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "informacoes_unidade_id", referencedColumnName = "id", nullable = false)
    private InformacoesUnidade informacoesUnidade;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    public Avaliacao() {}

    public Avaliacao(Cliente cliente, InformacoesUnidade informacoesUnidade, int nota) {
        this.cliente = cliente;
        this.informacoesUnidade = informacoesUnidade;
        this.nota = nota;
        this.dataHora = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public int getNota() { return nota; }
    public void setNota(int nota) { this.nota = nota; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public InformacoesUnidade getInformacoesUnidade() { return informacoesUnidade; }
    public void setInformacoesUnidade(InformacoesUnidade informacoesUnidade) { this.informacoesUnidade = informacoesUnidade; }
    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
}
