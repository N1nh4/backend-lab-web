package com.example.lab_web.Model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cliente extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;
    private String endereco;
    private String telefone;
    private int contribuicoes;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "contribuicao_id")
    private List<Contribuicao> contribuicoesLista;
    
    public Cliente() {
        
    }

    public Cliente(String nome, String email, String senha, String dataNascimento, String cpf, String endereco, String telefone) {
        super(nome, email, senha, dataNascimento);
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.contribuicoes = 0; 
        this.contribuicoesLista = new ArrayList<>();
        
    }

    public int getContribuicoes() {
        return contribuicoes;
    }

    public void imprimirCliente() {
        System.out.println("Nome: " + getNome());
        System.out.println("Email: " + getEmail());
        System.out.println("Data de Nascimento: " + getDataNascimento());
        System.out.println("CPF: " + cpf);
        System.out.println("Endereco: " + endereco);
        System.out.println("Telefone: " + telefone);
    }

    public void adicionarContribuicao(Contribuicao contribuicao) {
        this.contribuicoesLista.add(contribuicao);
        this.contribuicoes++;
    }

    public void cadastrarCliente() {
        System.out.println("Cliente cadastrado com sucesso!");
    }

    public void editarCliente() {
        System.out.println("Cliente editado com sucesso!");
    }

    public void excluirCliente() {
        System.out.println("Cliente excluído com sucesso!");
    }

}