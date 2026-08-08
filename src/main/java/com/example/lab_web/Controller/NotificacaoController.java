package com.example.lab_web.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lab_web.DTO.EmailDTO;
import com.example.lab_web.Service.NotificacaoService;

@RestController
@RequestMapping("/salvar-email-notificacao")
@CrossOrigin("*")
public class NotificacaoController {

    private NotificacaoService notificacaoService;

    public NotificacaoController(NotificacaoService notificacaoService) {
        this.notificacaoService = notificacaoService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> ativarNotificacao(@RequestBody EmailDTO dto) {
        Map<String, String> response = new HashMap<>();
        try {
            notificacaoService.assinar(dto.getEmail(), dto.getIdUnidade());
            response.put("resposta", "Notificação ativada com sucesso");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("resposta", "Erro ao ativar notificação: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping
    public ResponseEntity<Map<String, String>> desativarNotificacao(@RequestBody EmailDTO dto) {
        notificacaoService.cancelar(dto.getEmail(), dto.getIdUnidade());
        Map<String, String> response = new HashMap<>();
        response.put("resposta", "Notificação desativada com sucesso");
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<Long>> listarUnidadesInscritas(@RequestParam String email) {
        return ResponseEntity.ok(notificacaoService.listarUnidadesInscritas(email));
    }
}
