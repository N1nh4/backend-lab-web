package com.example.lab_web.Factory;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.example.lab_web.Model.Atualizacao;
import com.example.lab_web.Model.Status;
import com.example.lab_web.Model.Unidade;
import com.example.lab_web.Model.Usuario;

@Component("funcionarioFactory")
public class AtualizacaoFuncionarioFactory implements AtualizacaoFactory{
    
    @Override
    public Atualizacao criarAtualizacao(Status status, Usuario usuario, Unidade unidade) {
        Atualizacao atualizacao = new Atualizacao();
        atualizacao.setStatus(status);
        atualizacao.setUsuario(usuario);
        atualizacao.setUnidade(unidade);
        atualizacao.setConfiabilidade(2);
        atualizacao.setDataHora(LocalDateTime.now());
        return atualizacao;
    }
}
