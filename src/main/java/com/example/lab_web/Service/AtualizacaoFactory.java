package com.example.lab_web.Service;

import com.example.lab_web.Model.Atualizacao;
import com.example.lab_web.Model.Status;
import com.example.lab_web.Model.Usuario;

public abstract class AtualizacaoFactory {
    public abstract Atualizacao criarAtualizacao(Usuario usuario, Status status);
}
