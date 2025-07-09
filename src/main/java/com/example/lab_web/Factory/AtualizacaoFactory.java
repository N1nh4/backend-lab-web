package com.example.lab_web.Factory;
import com.example.lab_web.Model.Atualizacao;
import com.example.lab_web.Model.Status;
import com.example.lab_web.Model.Unidade;
import com.example.lab_web.Model.Usuario;

public interface AtualizacaoFactory {
    Atualizacao criarAtualizacao(Status status, Usuario usuario, Unidade unidade);
}
