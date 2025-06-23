package com.example.lab_web.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Administrador extends Usuario {
    private String cpf;
    private String endereco;
    private String telefone;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "gestor_unidade_id", referencedColumnName = "id")
    private GestorUnidade gestorUnidade;

    public Administrador() {}

    public Administrador(String nome, String email, String senha, String dataNascimento, String cpf, String endereco, String telefone) {
        super(nome, email, senha, dataNascimento);
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void cadastrarAdm() {
        System.out.println("Administrador cadastrado com sucesso!");
    }

    public void editarAdm() {
        System.out.println("Administrador editado com sucesso!");
    }

    public void excluirAdm() {
        System.out.println("Administrador excluído com sucesso!");
    }
    
}
