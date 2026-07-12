package com.example.lab_web.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.lab_web.DTO.UsuarioDTO;
import com.example.lab_web.Model.Cliente;
import com.example.lab_web.Model.Funcionario;
import com.example.lab_web.Model.Usuario;
import com.example.lab_web.Repository.AvaliacaoRepository;
import com.example.lab_web.Repository.ClienteRepository;
import com.example.lab_web.Repository.ComentarioRepository;
import com.example.lab_web.Repository.FuncionarioRepository;
import com.example.lab_web.Repository.UsuarioRepository;

@Service
public class UsuarioService {

    private UsuarioRepository ur;
    private ClienteRepository cr;
    private FuncionarioRepository fr;
    private ComentarioRepository comentarioRepository;
    private AvaliacaoRepository avaliacaoRepository;

    public UsuarioService(UsuarioRepository ur, ClienteRepository cr, FuncionarioRepository fr, ComentarioRepository comentarioRepository, AvaliacaoRepository avaliacaoRepository) {
        this.ur = ur;
        this.cr = cr;
        this.fr = fr;
        this.comentarioRepository = comentarioRepository;
        this.avaliacaoRepository = avaliacaoRepository;
    }

    public void criarConta(Cliente cliente) {
        cr.save(cliente);
    }

    public Optional<Cliente> buscarPorEmail(String email) {
        return cr.findByEmail(email);
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return cr.findById(id);
    }

    public void atualizar(Cliente cliente) {
        cr.save(cliente);
    }

    public Optional<Funcionario> buscarPorEmailFuncionario(String email) {
        return fr.findByEmail(email);
    }

    public void deletarConta(Long clienteId) {
        comentarioRepository.deleteByClienteId(clienteId);
        avaliacaoRepository.deleteByClienteId(clienteId);
        cr.deleteById(clienteId);
    }
    
}
