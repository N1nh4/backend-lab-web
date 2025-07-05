package com.example.lab_web.Service;

import org.springframework.stereotype.Service;

import com.example.lab_web.DTO.UsuarioDTO;
import com.example.lab_web.Model.Cliente;
import com.example.lab_web.Repository.UsuarioRepository;

@Service
public class UsuarioService {

    private UsuarioRepository ur;

    public UsuarioService(UsuarioRepository ur) {
        this.ur = ur;
    }

    public void criarConta(Cliente cliente) {
        ur.save(cliente);
    }
    
}
