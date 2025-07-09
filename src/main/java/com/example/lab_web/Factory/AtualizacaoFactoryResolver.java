package com.example.lab_web.Factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.lab_web.Model.Cliente;
import com.example.lab_web.Model.Funcionario;
import com.example.lab_web.Model.Usuario;

@Component
public class AtualizacaoFactoryResolver {
    @Autowired
    private AtualizacaoClienteFactory clienteFactory;

    @Autowired
    private AtualizacaoFuncionarioFactory funcionarioFactory;

    public AtualizacaoFactory resolveFactory(Usuario usuario) {
        if (usuario instanceof Funcionario) {
            return funcionarioFactory;
        } else if (usuario instanceof Cliente) {
            return clienteFactory;
        } else {
            throw new IllegalArgumentException("Tipo de usuário desconhecido: " + usuario.getClass().getName());
        }
    }
}
