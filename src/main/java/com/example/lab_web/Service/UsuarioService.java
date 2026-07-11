package com.example.lab_web.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.lab_web.DTO.UsuarioDTO;
import com.example.lab_web.Model.Cliente;
import com.example.lab_web.Model.Funcionario;
import com.example.lab_web.Model.Usuario;
import com.example.lab_web.Repository.ClienteRepository;
import com.example.lab_web.Repository.FuncionarioRepository;
import com.example.lab_web.Repository.UsuarioRepository;

@Service
public class UsuarioService {

    private UsuarioRepository ur;
    private ClienteRepository cr;
    private FuncionarioRepository fr;

    public UsuarioService(UsuarioRepository ur, ClienteRepository cr, FuncionarioRepository fr) {
        this.ur = ur;
        this.cr = cr;
        this.fr = fr;
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
    
}
