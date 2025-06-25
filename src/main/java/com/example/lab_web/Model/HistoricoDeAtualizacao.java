package com.example.lab_web.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class HistoricoDeAtualizacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "contribuicao_id")
    private List<Atualizacao> atualizacoes;
    
    @OneToOne()
    @JoinColumn(name = "unidade_id", referencedColumnName = "id")
    private Unidade unidade;

    public HistoricoDeAtualizacao() {
        
    }

    public HistoricoDeAtualizacao(Unidade unidade, List<Atualizacao> atualizacoes) {
        this.unidade = unidade;
        this.atualizacoes = atualizacoes;

    }

    public void imprimirHistorico() {
        System.out.println("Unidade ");
        System.out.println("Atualizações: ");
        for (Atualizacao atualizacao : atualizacoes) {
            atualizacao.imprimirAtualizacao();
        }
    }

    public void adicionarUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public void adicionarAtualizacao(Atualizacao atualizacao) {
        this.atualizacoes.add(atualizacao);
    }
    
}
