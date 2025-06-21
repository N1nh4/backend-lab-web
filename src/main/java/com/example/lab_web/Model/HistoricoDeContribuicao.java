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

@Entity
public class HistoricoDeContribuicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Cliente cliente;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "contribuicao_id")
    
    private List<Contribuicao> contribuicoes;

    public HistoricoDeContribuicao() {
        
    }
    
    public HistoricoDeContribuicao(Cliente cliente) {
        this.cliente = cliente;
        this.contribuicoes = new ArrayList<>();
    }
}


