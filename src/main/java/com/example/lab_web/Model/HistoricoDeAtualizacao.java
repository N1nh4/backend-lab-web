
package com.example.lab_web.Model;

import java.util.ArrayList;
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

    public HistoricoDeAtualizacao() {}

    public HistoricoDeAtualizacao(Unidade unidade, List<Atualizacao> atualizacoes) {
        this.unidade = unidade;
        if (atualizacoes != null) {
            this.atualizacoes = new ArrayList<>(atualizacoes);
        } else {
            this.atualizacoes = new ArrayList<>();
        }
        // Ao criar um Historico, ele também deve saber a que Unidade pertence.
        // E a Unidade deve ter seu Historico setado.
        if (unidade != null && unidade.getHistoricoDeAtualizacao() != this) {
            unidade.setHistoricoDeAtualizacao(this);
        }
    }

    // --- GETTERS E SETTERS Adicionados/Verificados ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Atualizacao> getAtualizacoes() {
        return atualizacoes;
    }

    public void setAtualizacoes(List<Atualizacao> atualizacoes) {
        this.atualizacoes = atualizacoes;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) { // ESTE É O SETTER NECESSÁRIO PARA MANTER A BIDIRECIONALIDADE
        this.unidade = unidade;
        // Importante para manter a bidirecionalidade consistente
        if (unidade != null && unidade.getHistoricoDeAtualizacao() != this) {
            unidade.setHistoricoDeAtualizacao(this);
        }
    }
    // --- FIM DOS GETTERS E SETTERS ---

    public void imprimirHistorico() {
        System.out.println("Histórico de Atualizações da Unidade: " + (unidade != null ? unidade.getId() : "N/A"));
        if (atualizacoes.isEmpty()) {
            System.out.println("Nenhuma atualização no histórico.");
        } else {
            for (Atualizacao atualizacao : atualizacoes) {
                atualizacao.imprimirAtualizacao();
            }
        }
    }

    public void adicionarAtualizacao(Atualizacao atualizacao) { // ESTE É O MÉTODO NECESSÁRIO
        if (this.atualizacoes == null) {
            this.atualizacoes = new ArrayList<>();
        }
        this.atualizacoes.add(atualizacao);
    }
}