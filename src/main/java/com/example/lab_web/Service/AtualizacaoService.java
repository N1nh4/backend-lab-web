package com.example.lab_web.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lab_web.Factory.AtualizacaoFactory;
import com.example.lab_web.Factory.AtualizacaoFactoryResolver;
import com.example.lab_web.Model.Atualizacao;
import com.example.lab_web.Model.Cliente;
import com.example.lab_web.Model.Status;
import com.example.lab_web.Model.Unidade;
import com.example.lab_web.Model.Usuario;
import com.example.lab_web.Repository.AtualizacaoRepository;
import com.example.lab_web.Repository.UsuarioRepository;

@Service
public class AtualizacaoService {

    @Autowired
    private AtualizacaoRepository atualizacaoRepository;

    @Autowired
    private AtualizacaoFactoryResolver factoryResolver;

    @Autowired
    private UnidadeService unidadeService; 

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private NotificacaoService notificacaoService;

    public Atualizacao registrar(Status status, Usuario usuario, Unidade unidade) {
        AtualizacaoFactory factory = factoryResolver.resolveFactory(usuario);
        Atualizacao atualizacao = factory.criarAtualizacao(status, usuario, unidade);
        
        atualizacaoRepository.save(atualizacao);

        // Incrementa contribuição se for cliente
        if (usuario instanceof Cliente) {
            Cliente cliente = (Cliente) usuario;
            cliente.incrementarContribuicoes();  
            usuarioRepository.save(cliente);    
        }

        // Atualiza o status da unidade com base nas últimas 2h
        unidadeService.gerarStatusAtual(unidade.getId());

        // Notifica os inscritos dessa unidade quando o status mudar
        try {
            notificacaoService.verificarMudancas(unidade.getId());
        } catch (Exception e) {
            System.err.println("Erro ao notificar inscritos (não crítico): " + e.getMessage());
        }

        return atualizacao;
    }
}
