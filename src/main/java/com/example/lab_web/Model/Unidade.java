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
    private Status status;
    
    @OneToOne(mappedBy = "unidade", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private HistoricoDeAtualizacao historicoDeAtualizacao;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "informacoes_unidade_id", referencedColumnName = "id")
    private InformacoesUnidade informacoesUnidade;

    public Unidade() {} 

    public Unidade(String status, InformacoesUnidade informacoesUnidade, HistoricoDeAtualizacao historicoDeAtualizacao) {
        this.status = Status.valueOf(status);
        this.informacoesUnidade = informacoesUnidade;
        this.historicoDeAtualizacao = historicoDeAtualizacao;
    }


    public void visualizarMapaUnidade() {
        System.out.println("Mapa da Unidade ");
    }

    public void buscarUnidade() {
        System.out.println("Unidade encontrada com sucesso!");
    }

    public void visualizarUnidade() {
        System.out.println("Informações da Unidade: ");
    }

    public void gerarStatusAtualUnidade() {
        System.out.println("Status atual da Unidade: " + status);
    }
}
