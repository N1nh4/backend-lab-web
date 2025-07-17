package com.example.lab_web.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lab_web.DTO.EmailDTO;
import com.example.lab_web.Model.ListaEmailsNotificacao;
import com.example.lab_web.Service.ListaEmailsService;

@RestController
@RequestMapping("/salvar-email-notificacao")
public class ListaEmailsController {

    private ListaEmailsService les;

    public ListaEmailsController(ListaEmailsService les) {
        this.les = les;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> salvarEmailNotificacao(@RequestBody EmailDTO ler) {
        String email = les.salvarEmail(ler);

        Map<String, String> response = new HashMap<>();
        response.put("resposta", "Email salvo com sucesso: " + email);

        return ResponseEntity.ok(response);
        
    }
}