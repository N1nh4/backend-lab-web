package com.example.lab_web.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lab_web.DTO.ClienteRankingDTO;
import com.example.lab_web.Repository.ClienteRepository;

@RestController
public class RankingController {

    private final ClienteRepository clienteRepository;

    public RankingController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping("/ranking")
    public List<ClienteRankingDTO> getRanking() {
        return clienteRepository.findTop10ByOrderByContribuicoesDesc()
                .stream()
                .map(cliente -> new ClienteRankingDTO(cliente.getNome(), cliente.getContribuicoes(), cliente.getFotoURL()))
                .toList();
    }
}
