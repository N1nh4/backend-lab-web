package com.example.lab_web.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lab_web.Model.Cliente;
import com.example.lab_web.Service.UsuarioService;

@RestController
@RequestMapping("/criar-conta")
public class UsuarioController {

    private UsuarioService us;
    
    public UsuarioController(UsuarioService us) {
        this.us = us;
    }

    @PostMapping
    public ResponseEntity<?> criarConta(@RequestBody Cliente cliente) {
        us.criarConta(cliente);
        return ResponseEntity.ok().build();
    }
}
