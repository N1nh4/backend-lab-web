package com.example.lab_web.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Funcionario extends Usuario {
    private String cpf;
    private String endereco;
    private String telefone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unidade_id")
    private Unidade unidade;

    public Funcionario() {
        super();
    }

    public Funcionario(String nome, String email, String senha, String cpf, String endereco, String telefone, Unidade unidade) {
        super(nome, email, senha);
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.unidade = unidade;
    }

}
