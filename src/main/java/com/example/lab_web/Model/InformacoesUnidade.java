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
    @JoinColumn(name = "informacoes_unidade_id")
    private List<Avaliacao> avaliacao;

    public InformacoesUnidade() {
        this.avaliacao = new ArrayList<>();
    }

    public InformacoesUnidade(List<Avaliacao> avaliacao, String nome, Endereco endereco, String telefone,
            String imagemURL) {
        this.avaliacao = avaliacao != null ? avaliacao : new ArrayList<>();
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.imagemURL = imagemURL;
    }

    public List<Avaliacao> getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(List<Avaliacao> avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getImagemURL() {
        return imagemURL;
    }

    public void setImagemURL(String imagemURL) {
        this.imagemURL = imagemURL;
    }

    public void imprimirInformacoes() {
        System.out.println("Nome da Unidade: " + nome);
        System.out.println("Endereço: " + (endereco != null ? "Detalhes do Endereco" : "N/A"));

        System.out.println("Telefone: " + telefone);
        System.out.println("URL da Imagem: " + imagemURL);
        System.out.println("Avaliações: (" + avaliacao.size() + " totais)");
        for (Avaliacao aval : avaliacao) {
            aval.imprimirAvaliacao();
        }
    }
}
