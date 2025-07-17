package com.example.lab_web.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lab_web.DTO.LoginDTO;
import com.example.lab_web.DTO.UsuarioDTO;
import com.example.lab_web.Model.Cliente;
import com.example.lab_web.Model.Funcionario;
import com.example.lab_web.Model.Usuario;
import com.example.lab_web.Service.EmailService;
import com.example.lab_web.Service.UsuarioService;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class UsuarioController {

    private UsuarioService us;
    private EmailService es;
    
    public UsuarioController(UsuarioService us, EmailService es) {
        this.us = us;
        this.es = es;
    }

    @PostMapping("/criar-conta")
    public ResponseEntity<Map<String, String>> criarConta(@RequestBody Cliente cliente) {
        us.criarConta(cliente);
        Map<String, String> resposta = new HashMap<>();
        resposta.put("mensagem", "Conta criada com sucesso");

        return ResponseEntity.ok(resposta);
    }

   @PostMapping("/entrar")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO cliente) {
    
        Map<String, String> resposta = new HashMap<>();
    
        Optional<Cliente> clienteOpt = us.buscarPorEmail(cliente.getEmail());

        if (clienteOpt.isEmpty()) {
            resposta.put("mensagem", "Usuário não encontrado");
            return ResponseEntity.status(404).body(resposta);
        } 

        Cliente clienteEncontrado = clienteOpt.get();

        if (!clienteEncontrado.getSenha().equals(cliente.getSenha())) {
            resposta.put("mensagem", "Senha inválida");
            return ResponseEntity.status(401).body(resposta);
        }

        resposta.put("mensagem", "Login realizado com sucesso");
        resposta.put("usuarioId", String.valueOf(clienteEncontrado.getId())); // ✅ Aqui
        resposta.put("nome", clienteEncontrado.getNome());
        resposta.put("email", clienteEncontrado.getEmail());

        return ResponseEntity.ok(resposta);
    }


    @PostMapping("/entrar-funcionario")
    public ResponseEntity<Map<String, String>> loginFuncionario(@RequestBody LoginDTO funcionario) {
    
        Map<String, String> resposta = new HashMap<>();
    
        Optional<Funcionario> funcionarioOpt = us.buscarPorEmailFuncionario(funcionario.getEmail());

        if (funcionarioOpt.isEmpty()) {
            resposta.put("mensagem", "Usuário não encontrado");
            return ResponseEntity.status(404).body(resposta);
        } 

        Funcionario funcionarioEncontrado = funcionarioOpt.get();

        if (!funcionarioEncontrado.getSenha().equals(funcionario.getSenha())) {
            resposta.put("mensagem", "Senha inválida");
            return ResponseEntity.status(401).body(resposta);
        }

        resposta.put("mensagem", "Login realizado com sucesso");
        resposta.put("usuarioId", String.valueOf(funcionarioEncontrado.getId())); // ✅ Aqui
        resposta.put("nome", funcionarioEncontrado.getNome());
        resposta.put("email", funcionarioEncontrado.getEmail());

        return ResponseEntity.ok(resposta);
    }

}
