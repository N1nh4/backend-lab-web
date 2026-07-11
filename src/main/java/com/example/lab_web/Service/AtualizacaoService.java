package com.example.lab_web.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lab_web.DTO.EmailDTO;
import com.example.lab_web.Factory.AtualizacaoFactory;
import com.example.lab_web.Factory.AtualizacaoFactoryResolver;
import com.example.lab_web.Model.Atualizacao;
import com.example.lab_web.Model.Cliente;
import com.example.lab_web.Model.ListaEmailsNotificacao;
import com.example.lab_web.Model.Status;
import com.example.lab_web.Model.Unidade;
import com.example.lab_web.Model.Usuario;
import com.example.lab_web.Repository.AtualizacaoRepository;
import com.example.lab_web.Repository.ListaEmailsRepository;
import com.example.lab_web.Repository.UnidadeRepository;
import com.example.lab_web.Repository.UsuarioRepository;

@Service
public class AtualizacaoService {

    @Autowired
    private EmailService es;

    @Autowired
    private ListaEmailsRepository ler;

    @Autowired
    private UnidadeRepository ur;

    @Autowired
    private AtualizacaoRepository atualizacaoRepository;

    @Autowired
    private AtualizacaoFactoryResolver factoryResolver;

    @Autowired
    private UnidadeService unidadeService; 

    @Autowired
    private UsuarioRepository usuarioRepository;

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

        // Envia o email
        Optional<Unidade> u = ur.findById(unidade.getId());
        System.out.println("PASSOU aqui");
        
        if (u.isPresent()) {
            Unidade uu = u.get();

            ListaEmailsNotificacao emailDTO = new ListaEmailsNotificacao();
            emailDTO.setEmail(usuario.getEmail());
            ler.save(emailDTO);

            System.out.println("ENTROU NO IF");

            if (u.get().getStatus() == Status.VAZIO) {
                System.out.println("STATUS VAZIO E ENTROU PARA ENVIAR EMAIL");
                try {
                    List<ListaEmailsNotificacao> emails = ler.findAll();
                    es.enviarEmail(emails);
                } catch (Exception e) {
                    System.err.println("Erro ao enviar email (não crítico): " + e.getMessage());
                }
            }
        }

        return atualizacao;
    }
}
