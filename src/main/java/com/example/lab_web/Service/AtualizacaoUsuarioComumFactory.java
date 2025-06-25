package com.example.lab_web.Service;

import com.example.lab_web.Model.Atualizacao;
import com.example.lab_web.Model.Status;
import com.example.lab_web.Model.Usuario;
import com.example.lab_web.Model.Cliente;
import com.example.lab_web.Model.NivelConfiabilidade;;


public class AtualizacaoUsuarioComumFactory extends AtualizacaoFactory{
    @Override
    public Atualizacao criarAtualizacao(Usuario usuario, Status status) {
        System.out.println("Criando atualização por usuário comum (Cliente)");

        if (usuario instanceof Cliente) {
            return new Atualizacao(usuario, status, NivelConfiabilidade.BAIXA);
        } 
        return new Atualizacao(usuario, status, NivelConfiabilidade.BAIXA);
    }
}




