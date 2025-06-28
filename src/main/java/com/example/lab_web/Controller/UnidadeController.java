package com.example.lab_web.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lab_web.DTO.UnidadeDTO;
import com.example.lab_web.Model.InformacoesUnidade;
import com.example.lab_web.Model.Unidade;
import com.example.lab_web.Service.UnidadeService;

@RestController
@RequestMapping("/unidades")
public class UnidadeController {

    private UnidadeService us;

    public UnidadeController(UnidadeService us) {
        this.us = us;

    }

    @GetMapping
    public ResponseEntity<List<UnidadeDTO>> getUnidades() {
        List<UnidadeDTO> unidades =  us.getUnidades();
        return ResponseEntity.ok().body(unidades);
    }
}