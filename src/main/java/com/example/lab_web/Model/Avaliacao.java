package com.example.lab_web.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comentario;
    private int nota;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "informacoes_unidade_id", referencedColumnName = "id", nullable = false)
    private InformacoesUnidade informacoesUnidade;

    public Avaliacao() {
    }

    public Avaliacao(String comentario, Cliente cliente, int nota, InformacoesUnidade informacoesUnidade) {
        this.comentario = comentario;
        this.cliente = cliente;
        this.nota = nota;
        this.informacoesUnidade = informacoesUnidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public InformacoesUnidade getInformacoesUnidade() {
        return informacoesUnidade;
    }

    public void setInformacoesUnidade(InformacoesUnidade informacoesUnidade) {
        this.informacoesUnidade = informacoesUnidade;
    }

    public void adicionarAvaliacao(String comentario) {
        this.comentario = comentario;
    }

    public void imprimirAvaliacao() {
        System.out.println("Comentario: '" + comentario + "', Nota: " + nota + ", Cliente: "
                + (cliente != null ? cliente.getNome() : "N/A"));
    }
}
