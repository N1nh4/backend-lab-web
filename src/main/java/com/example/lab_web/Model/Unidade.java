package com.example.lab_web.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Unidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status statusAvaliacaoPadrao;

    @Enumerated(EnumType.STRING)
    private SemaforoStatus semaforoAtual;

    @OneToOne(mappedBy = "unidade", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private HistoricoDeAtualizacao historicoDeAtualizacao;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "informacoes_unidade_id", referencedColumnName = "id")
    private InformacoesUnidade informacoesUnidade;

    public Unidade() {
    }

    public Unidade(InformacoesUnidade informacoesUnidade, HistoricoDeAtualizacao historicoDeAtualizacao) {
        this.informacoesUnidade = informacoesUnidade;
        this.historicoDeAtualizacao = historicoDeAtualizacao;
        this.semaforoAtual = SemaforoStatus.INDETERMINADO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InformacoesUnidade getInformacoesUnidade() {
        return informacoesUnidade;
    }

    public void setInformacoesUnidade(InformacoesUnidade informacoesUnidade) {
        this.informacoesUnidade = informacoesUnidade;
    }

    public HistoricoDeAtualizacao getHistoricoDeAtualizacao() {
        return historicoDeAtualizacao;
    }

    public void setHistoricoDeAtualizacao(HistoricoDeAtualizacao historicoDeAtualizacao) {
        this.historicoDeAtualizacao = historicoDeAtualizacao;
    }

    public SemaforoStatus getSemaforoAtual() {
        return semaforoAtual;
    }

    public void setSemaforoAtual(SemaforoStatus semaforoAtual) {
        this.semaforoAtual = semaforoAtual;
    }

    public void visualizarMapaUnidade() {
        System.out.println("Mapa da Unidade ");
    }

    public void buscarUnidade() {
        System.out.println("Unidade encontrada com sucesso!");
    }

    public void visualizarUnidade() {
        System.out.println("Informações da Unidade: ");
        if (informacoesUnidade != null) {
            informacoesUnidade.imprimirInformacoes();
        }
    }

    public void gerarStatusAtualUnidade() {
        System.out.println("Status atual da Unidade (Semáforo): " + semaforoAtual);
        if (informacoesUnidade != null && informacoesUnidade.getAvaliacao() != null) {
            System.out.println("Baseado em " + informacoesUnidade.getAvaliacao().size() + " avaliações recentes.");
        }
    }
}
