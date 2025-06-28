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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "comentario_id", referencedColumnName = "id", nullable = false)
    private Comentario textoComentario;

    private int nota;
    
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id", nullable = false)
    private Cliente cliente;

    public Avaliacao() {}

    public Avaliacao(Comentario comentario, Cliente cliente, int nota) {
        this.textoComentario = comentario;
        this.cliente = cliente;
        this.nota = nota;
    }

    public void adicionarAvaliacao(Comentario comentario) {
        this.textoComentario = comentario;
    }

    public void imprimirAvaliacao() {
        System.out.println("Comentario: " + this.textoComentario);
        System.out.println("Usuario: " + cliente.getNome());
        System.out.println("Nota: " + this.nota);
    }

    public void buscarAvalicao() {
        System.out.println("Nota 4.0");
    }
}
