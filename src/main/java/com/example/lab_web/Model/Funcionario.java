package com.example.lab_web.Model;

// Remova as importações não utilizadas se elas ficarem órfãs após a remoção da lista
// import java.util.ArrayList; // Provavelmente não será mais necessário
// import java.util.List;     // Provavelmente não será mais necessário

// Remova as anotações não utilizadas se elas ficarem órfãs
// import jakarta.persistence.CascadeType;
// import jakarta.persistence.FetchType;
// import jakarta.persistence.OneToMany;

import jakarta.persistence.Entity;
// Mantém as outras importações que são usadas

@Entity
public class Funcionario extends Usuario {
    private String cpf;
    private String endereco;
    private String telefone;
    private int contribuicoes; // Se 'contribuicoes' aqui for um contador de algo que o funcionário faz, tudo bem.
                               // Se for um contador de contribuições no sentido de Cliente, pode ser removido também.

    // REMOVIDO:
    // private List<Contribuicao> contribuicoesLista = new ArrayList<>();

    public Funcionario() {} // Construtor padrão (precisa existir para JPA)

    public Funcionario(String nome, String email, String senha, String dataNascimento, String cpf, String endereco, String telefone) {
        super(nome, email, senha, dataNascimento);
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.contribuicoes = 0; // Mantém se o atributo 'contribuicoes' for relevante para Funcionario.
        // REMOVIDO: this.contribuicoesLista = new ArrayList<>();
    }

    // Adicione getters e setters para os campos existentes se ainda não os tiver
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public int getContribuicoes() { return contribuicoes; }
    public void setContribuicoes(int contribuicoes) { this.contribuicoes = contribuicoes; }


    // Métodos específicos de Funcionario
    public void registrarPonto() {
        System.out.println("Ponto de funcionário registrado.");
    }
    
    public void realizarTarefaAdministrativa() {
        System.out.println("Tarefa administrativa realizada!");
    }
}