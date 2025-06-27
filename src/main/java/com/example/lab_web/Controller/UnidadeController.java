package com.example.lab_web.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lab_web.Model.InformacoesUnidade;
import com.example.lab_web.Model.Unidade;

@RestController
@RequestMapping("/unidades")
public class UnidadeController {

    @GetMapping
    public List<InformacoesUnidade> getUnidades() {
        private List<Unidade> unidades =  ;
    }
}