package com.example.lab_web.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class GestorUnidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public GestorUnidade() {}

    public void cadastrarUnidade() {
        System.out.println("Unidade cadastrada com sucesso!");
    }

    public void editarUnidade() {
        System.out.println("Unidade editada com sucesso!");
    }

    public void excluirUnidade(Unidade unidade) {
        System.out.println("Unidade excluída com sucesso!");
    }
}
