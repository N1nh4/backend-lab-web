package com.example.lab_web.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lab_web.DTO.UnidadeDTO;
import com.example.lab_web.DTO.UnidadePaginaDTO;
import com.example.lab_web.Model.Atualizacao;
import com.example.lab_web.Service.ClienteService;
import com.example.lab_web.Service.UnidadeService;

import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class UnidadeController {

    private UnidadeService us;
    private ClienteService cs;

    public UnidadeController(UnidadeService us, ClienteService cs) {
        this.us = us;
        this.cs = cs;

    }

    @GetMapping
    public ResponseEntity<List<UnidadeDTO>> getUnidades() {
        List<UnidadeDTO> unidades =  us.getUnidades();
        return ResponseEntity.ok().body(unidades);
    }

    @GetMapping("/unidade/{id}")
    public ResponseEntity<UnidadePaginaDTO> getUnidade(@PathVariable Long id) {
        UnidadePaginaDTO unidade =  us.getUnidade(id);
        return ResponseEntity.ok().body(unidade);
    }

    @PostMapping("/unidade/{id}/registrar-lotacao")
    public ResponseEntity<String> registrarLotacao(@RequestBody AtualizacaoDTO atualizacaoCliente, @PathVariable Long id) {
        System.out.println("Funcionando");
        System.out.println(atualizacaoCliente);
        cs.incrementarContribuicao((long)atualizacaoCliente.getIdUsuario());
        return ResponseEntity.status(HttpStatus.CREATED).body("Avaliação salva!");
    }
}