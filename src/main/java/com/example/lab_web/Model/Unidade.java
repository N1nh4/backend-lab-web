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
import jakarta.persistence.OneToOne;

@Entity
public class Unidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status;
    
    // MappedBy indica que a propriedade 'unidade' na classe HistoricoDeAtualizacao é a proprietária da relação.
    @OneToOne(mappedBy = "unidade", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private HistoricoDeAtualizacao historicoDeAtualizacao;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "informacoes_unidade_id", referencedColumnName = "id")
    private InformacoesUnidade informacoesUnidade;

    public Unidade() {} 

    // Construtor ajustado para receber InformacoesUnidade e opcionalmente HistoricoDeAtualizacao
    public Unidade(Status status, InformacoesUnidade informacoesUnidade) {
        this.status = status;
        this.informacoesUnidade = informacoesUnidade;
        // O HistoricoDeAtualizacao será criado sob demanda ou em outro lugar
    }

    // --- GETTERS E SETTERS Adicionados/Verificados ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) { // ESTE É O SETTER NECESSÁRIO PARA UNIDADESERVICE
        this.status = status;
    }

    public HistoricoDeAtualizacao getHistoricoDeAtualizacao() {
        return historicoDeAtualizacao;
    }

    public void setHistoricoDeAtualizacao(HistoricoDeAtualizacao historicoDeAtualizacao) { // ESTE É O SETTER NECESSÁRIO
        this.historicoDeAtualizacao = historicoDeAtualizacao;
        // Importante para manter a bidirecionalidade consistente
        if (historicoDeAtualizacao != null && historicoDeAtualizacao.getUnidade() != this) {
            historicoDeAtualizacao.setUnidade(this);
        }
    }

    public InformacoesUnidade getInformacoesUnidade() {
        return informacoesUnidade;
    }

    public void setInformacoesUnidade(InformacoesUnidade informacoesUnidade) {
        this.informacoesUnidade = informacoesUnidade;
    }
    // --- FIM DOS GETTERS E SETTERS ---

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
        } else {
            System.out.println("Nenhuma informação de unidade disponível.");
        }
    }

    public void gerarStatusAtualUnidade() {
        System.out.println("Status atual da Unidade: " + status);
    }
}