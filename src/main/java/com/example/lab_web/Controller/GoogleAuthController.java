package com.example.lab_web.Controller;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.lab_web.Model.Cliente;
import com.example.lab_web.Repository.ClienteRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class GoogleAuthController {

    private final ClienteRepository clienteRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public GoogleAuthController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @PostMapping("/auth/google")
    public ResponseEntity<Map<String, String>> googleAuth(@RequestBody Map<String, String> body) {
        String idToken = body.get("idToken");
        Map<String, String> resposta = new HashMap<>();

        if (idToken == null || idToken.isBlank()) {
            resposta.put("mensagem", "ID token não fornecido");
            return ResponseEntity.badRequest().body(resposta);
        }

        try {
            String url = "https://oauth2.googleapis.com/tokeninfo?id_token=" + idToken;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                resposta.put("mensagem", "Token Google inválido");
                return ResponseEntity.status(401).body(resposta);
            }

            JsonNode json = objectMapper.readTree(response.body());

            String email = json.get("email").asText();
            String nome = json.has("name") ? json.get("name").asText() : email.split("@")[0];
            String fotoURL = json.has("picture") ? json.get("picture").asText() : null;

            Optional<Cliente> clienteOpt = clienteRepository.findByEmail(email);
            Cliente cliente;

            if (clienteOpt.isPresent()) {
                cliente = clienteOpt.get();
                if (fotoURL != null && cliente.getFotoURL() == null) {
                    cliente.setFotoURL(fotoURL);
                    clienteRepository.save(cliente);
                }
            } else {
                cliente = new Cliente();
                cliente.setNome(nome);
                cliente.setEmail(email);
                cliente.setSenha("google-auth");
                cliente.setFotoURL(fotoURL);
                clienteRepository.save(cliente);
            }

            resposta.put("mensagem", "Login realizado com sucesso");
            resposta.put("usuarioId", String.valueOf(cliente.getId()));
            resposta.put("nome", cliente.getNome());
            resposta.put("email", cliente.getEmail());
            if (cliente.getFotoURL() != null) {
                resposta.put("fotoURL", cliente.getFotoURL());
            }
            return ResponseEntity.ok(resposta);

        } catch (Exception e) {
            e.printStackTrace();
            resposta.put("mensagem", "Erro ao validar token Google");
            return ResponseEntity.status(500).body(resposta);
        }
    }
}
