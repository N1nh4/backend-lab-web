package com.example.lab_web.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String senha;

    @Column(name = "foto_url", columnDefinition = "TEXT")
    private String fotoURL;

    public Usuario() {}

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    //public String getDataNascimento() {
    //    return dataNascimento;
    //}


    public void imprimirUsuario() {
        System.out.println("Nome: " + nome);
        System.out.println("Email: " + email);
    //    System.out.println("Data de Nascimento: " + dataNascimento);
    }

    public void login() {
        System.out.println("Logado com sucesso!");
    }

    public void logout() {
        System.out.println("Deslogado com sucesso!");
    }

    public void recuperarSenha() {
        System.out.println("Senha recuperada com sucesso!");
    }   

    
}
