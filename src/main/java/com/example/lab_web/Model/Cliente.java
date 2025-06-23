package com.example.lab_web.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

@Entity
public class Cliente extends Usuario {

    private String cpf;
    private String endereco;
    private String telefone;
    private int contribuicoes;

    
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Contribuicao> contribuicoesLista = new ArrayList<>();
    
    public Cliente() {
        this.contribuicoesLista = new ArrayList<>();
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
        contribuicao.setCliente(this);
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

    public List<Contribuicao> getContribuicoesLista() {
        return contribuicoesLista;
    }

    public void setContribuicoesLista(List<Contribuicao> contribuicoesLista) {
        this.contribuicoesLista = contribuicoesLista;
    }
}