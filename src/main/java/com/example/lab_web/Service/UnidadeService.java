package com.example.lab_web.Service;

import com.example.lab_web.Model.Atualizacao;
import com.example.lab_web.Model.Status;
import com.example.lab_web.Model.Unidade;
import com.example.lab_web.Model.Usuario;
import com.example.lab_web.Model.Cliente;
import com.example.lab_web.Model.Funcionario;
import com.example.lab_web.Model.HistoricoDeAtualizacao;
import com.example.lab_web.Repository.AtualizacaoRepository;
import com.example.lab_web.Repository.UnidadeRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@Service
public class UnidadeService {

    @Autowired
    private AtualizacaoRepository atualizacaoRepository;
    
    @Autowired
    private UnidadeRepository unidadeRepository;

    /**
     * Registra o status de uma unidade, determinando o nível de confiabilidade
     * com base no tipo de usuário que realiza o registro.
     *
     * @param unidadeId O ID da unidade a ser atualizada.
     * @param usuarioQueRegistrou O usuário que está registrando o status.
     * @param novoStatus O novo status da unidade.
     * @throws RuntimeException se a unidade não for encontrada.
     * @throws IllegalArgumentException se o usuário não for autorizado ou for de um tipo desconhecido para registro de status.
     */
    public void registrarStatusUnidade(Long unidadeId, Usuario usuarioQueRegistrou, Status novoStatus) {
        Unidade unidade = unidadeRepository.findById(unidadeId)
                                .orElseThrow(() -> new RuntimeException("Unidade não encontrada!"));

        AtualizacaoFactory factory;

        // A lógica para decidir qual fábrica usar com base no tipo de usuário
        if (usuarioQueRegistrou instanceof Funcionario) {
            factory = new AtualizacaoFuncionarioFactory();
        } else if (usuarioQueRegistrou instanceof Cliente) {
            factory = new AtualizacaoUsuarioComumFactory();
        } else {
            // Se o usuário não for um Funcionario nem um Cliente (ex: Administrador ou tipo desconhecido),
            // ele não é autorizado a registrar status neste sistema.
            throw new IllegalArgumentException("Usuário do tipo " + usuarioQueRegistrou.getClass().getSimpleName() + 
                                               " não autorizado a registrar status da unidade.");
        }

        // Usa o Factory Method para criar a Atualizacao com a confiabilidade implícita
        Atualizacao novaAtualizacao = factory.criarAtualizacao(usuarioQueRegistrou, novoStatus);
        
        // Salva a atualização no banco de dados
        atualizacaoRepository.save(novaAtualizacao);

        // Atualiza o status atual da unidade
        unidade.setStatus(novoStatus); 
        
        // Garante que o HistoricoDeAtualizacao exista antes de adicionar uma atualização
        if (unidade.getHistoricoDeAtualizacao() == null) {
            // Se não houver histórico, cria um novo
            unidade.setHistoricoDeAtualizacao(new HistoricoDeAtualizacao(unidade, new ArrayList<>()));
        }
        // Adiciona a nova atualização ao histórico da unidade
        unidade.getHistoricoDeAtualizacao().adicionarAtualizacao(novaAtualizacao);
        
        // Salva a unidade para persistir as mudanças no status e no histórico
        unidadeRepository.save(unidade);
        
        System.out.println("Status da unidade " + unidadeId + " atualizado para " + novoStatus.name() + 
                           " por " + usuarioQueRegistrou.getClass().getSimpleName() + 
                           " com confiabilidade " + novaAtualizacao.getConfiabilidade().name());
    }

}