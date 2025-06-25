package com.example.lab_web.Service;

import com.example.lab_web.Model.Atualizacao;
import com.example.lab_web.Model.NivelConfiabilidade;
import com.example.lab_web.Model.Status;
import com.example.lab_web.Model.Usuario;
import com.example.lab_web.Model.Funcionario;

public class AtualizacaoFuncionarioFactory extends AtualizacaoFactory {
    @Override
    public Atualizacao criarAtualizacao(Usuario usuario, Status status) {
        System.out.println("Criando atualização por funcionário (Funcionario)");
        if (usuario instanceof Funcionario) {
            return new Atualizacao(usuario, status, NivelConfiabilidade.ALTA);
        }
        return new Atualizacao(usuario, status, NivelConfiabilidade.MEDIA);
    }
    
}
