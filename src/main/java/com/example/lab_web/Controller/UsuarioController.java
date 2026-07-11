package com.example.lab_web.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        resposta.put("usuarioId", String.valueOf(clienteEncontrado.getId()));
        resposta.put("nome", clienteEncontrado.getNome());
        resposta.put("email", clienteEncontrado.getEmail());
        if (clienteEncontrado.getFotoURL() != null) {
            resposta.put("fotoURL", clienteEncontrado.getFotoURL());
        }

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
        resposta.put("usuarioId", String.valueOf(funcionarioEncontrado.getId()));
        resposta.put("nome", funcionarioEncontrado.getNome());
        resposta.put("email", funcionarioEncontrado.getEmail());
        if (funcionarioEncontrado.getFotoURL() != null) {
            resposta.put("fotoURL", funcionarioEncontrado.getFotoURL());
        }

        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<Map<String, String>> buscarUsuario(@PathVariable Long id) {
        Map<String, String> resposta = new HashMap<>();
        Optional<Cliente> clienteOpt = us.buscarPorId(id);

        if (clienteOpt.isEmpty()) {
            resposta.put("mensagem", "Usuário não encontrado");
            return ResponseEntity.status(404).body(resposta);
        }

        Cliente cliente = clienteOpt.get();
        resposta.put("usuarioId", String.valueOf(cliente.getId()));
        resposta.put("nome", cliente.getNome());
        resposta.put("email", cliente.getEmail());
        resposta.put("telefone", cliente.getTelefone() != null ? cliente.getTelefone() : "");
        resposta.put("endereco", cliente.getEndereco() != null ? cliente.getEndereco() : "");
        resposta.put("cpf", cliente.getCpf() != null ? cliente.getCpf() : "");
        if (cliente.getFotoURL() != null) {
            resposta.put("fotoURL", cliente.getFotoURL());
        }

        return ResponseEntity.ok(resposta);
    }

    @PutMapping("/usuario/{id}/foto")
    public ResponseEntity<Map<String, String>> atualizarFoto(@PathVariable Long id, @RequestBody Map<String, String> body) {
        Map<String, String> resposta = new HashMap<>();
        Optional<Cliente> clienteOpt = us.buscarPorId(id);

        if (clienteOpt.isEmpty()) {
            resposta.put("mensagem", "Usuário não encontrado");
            return ResponseEntity.status(404).body(resposta);
        }

        Cliente cliente = clienteOpt.get();
        cliente.setFotoURL(body.get("fotoURL"));
        us.atualizar(cliente);

        resposta.put("mensagem", "Foto atualizada com sucesso");
        return ResponseEntity.ok(resposta);
    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity<Map<String, String>> atualizarUsuario(@PathVariable Long id, @RequestBody Map<String, String> body) {
        Map<String, String> resposta = new HashMap<>();
        Optional<Cliente> clienteOpt = us.buscarPorId(id);

        if (clienteOpt.isEmpty()) {
            resposta.put("mensagem", "Usuário não encontrado");
            return ResponseEntity.status(404).body(resposta);
        }

        Cliente cliente = clienteOpt.get();
        if (body.containsKey("nome")) cliente.setNome(body.get("nome"));
        if (body.containsKey("email")) cliente.setEmail(body.get("email"));
        if (body.containsKey("telefone")) cliente.setTelefone(body.get("telefone"));
        if (body.containsKey("endereco")) cliente.setEndereco(body.get("endereco"));
        us.atualizar(cliente);

        resposta.put("mensagem", "Dados atualizados com sucesso");
        return ResponseEntity.ok(resposta);
    }

    @PutMapping("/usuario/{id}/senha")
    public ResponseEntity<Map<String, String>> atualizarSenha(@PathVariable Long id, @RequestBody Map<String, String> body) {
        Map<String, String> resposta = new HashMap<>();
        Optional<Cliente> clienteOpt = us.buscarPorId(id);

        if (clienteOpt.isEmpty()) {
            resposta.put("mensagem", "Usuário não encontrado");
            return ResponseEntity.status(404).body(resposta);
        }

        Cliente cliente = clienteOpt.get();
        String senhaAtual = body.get("senhaAtual");
        String novaSenha = body.get("novaSenha");

        if (!cliente.getSenha().equals(senhaAtual)) {
            resposta.put("mensagem", "Senha atual incorreta");
            return ResponseEntity.status(401).body(resposta);
        }

        cliente.setSenha(novaSenha);
        us.atualizar(cliente);

        resposta.put("mensagem", "Senha atualizada com sucesso");
        return ResponseEntity.ok(resposta);
    }

}
