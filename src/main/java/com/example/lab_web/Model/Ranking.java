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
public class Ranking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ranking_id")
    private List<Cliente> cliente = new ArrayList<>(); 

    public List<Cliente> getCliente() {
        return cliente;
    }


    public Ranking() {
        this.cliente = new ArrayList<>();
    }   


    public void imprimirRanking() {
        System.out.println("Ranking dos Clientes:");
        for (Cliente cliente : cliente) {
            System.out.println(cliente.getNome() + " - " + cliente.getContribuicoes() + " contribuições");
        }
    }

    public void adicionarCliente(Cliente cliente) {
        this.cliente.add(cliente);
    }
}
