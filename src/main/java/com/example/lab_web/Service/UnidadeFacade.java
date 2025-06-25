package com.example.lab_web.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lab_web.DTO.UnidadeDTO;
import com.example.lab_web.Model.Status;
import com.example.lab_web.Model.Unidade;
import com.example.lab_web.Repository.HistoricoAtualizacaoRepository;

@Service
public class UnidadeFacade {

    @Autowired
    private UnidadeService unidadeService;

    @Autowired
    private AvaliacaoService avaliacaoService;


    public UnidadeDTO obterInformacoes(Long id) {
        Unidade unidade = unidadeService.buscar(id);
        Status statusAtual = unidadeService.buscarStatusUnidade(id);
        List<String> comentarios = avaliacaoService.buscarComentariosPorIdUnidade(id);

        return new UnidadeDTO(unidade, statusAtual, comentarios);
    }
}