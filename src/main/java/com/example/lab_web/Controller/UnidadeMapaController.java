package com.example.lab_web.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lab_web.DTO.UnidadeMapaDTO;
import com.example.lab_web.Service.UnidadeMapaService;

@RestController
@RequestMapping("/mapa")
public class UnidadeMapaController {
    @Autowired
    private UnidadeMapaService unidadeMapaService;

    @GetMapping
    public List<UnidadeMapaDTO> listarUnidadesMapa() {
        return unidadeMapaService.listarTodas();
    }

}
