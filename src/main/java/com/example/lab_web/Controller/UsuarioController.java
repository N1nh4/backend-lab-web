package com.example.lab_web.Controller;

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<Map<String, String>> criarConta(@RequestBody Cliente cliente) {
        us.criarConta(cliente);
        Map<String, String> resposta = new HashMap<>();
        resposta.put("mensagem", "Conta criada com sucesso");
        return ResponseEntity.ok(resposta);
    }
}
