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
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class InformacoesUnidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String telefone;
    private String imagemURL;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "avaliacao_id")
    private List<Avaliacao> avaliacao;
    
    public InformacoesUnidade() {}
    
    public InformacoesUnidade(List<Avaliacao> avaliacao, String nome, Endereco endereco, String telefone, String imagemURL) {
        this.avaliacao = avaliacao;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.imagemURL = imagemURL;
    }
    public void imprimirInformacoes() {
        System.out.println("Status: ");
        System.out.println("Avaliações: ");
        for (Avaliacao avaliacao : avaliacao) {
            avaliacao.imprimirAvaliacao();
        }
    }
    

}