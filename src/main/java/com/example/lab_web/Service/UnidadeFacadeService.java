package com.example.lab_web.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lab_web.DTO.UnidadeDTO;
import com.example.lab_web.Model.Status;
import com.example.lab_web.Model.Unidade;

@Service
public class UnidadeFacadeService {

    @Autowired
    private UnidadeService unidadeService;

    @Autowired
    private HistoricoService historicoService;

    @Autowired
    private RankingService rankingService;

    public UnidadeDTO obterInformacoes(Long id) {
        Unidade unidade = unidadeService.buscar(id);
        Status statusAtual = unidade.gerarStatusAtual();
        List<String> comentarios = historicoService.buscarComentarios(id);
        int ranking = rankingService.obterPontuacao(unidade);

        return new UnidadeDTO(unidade, statusAtual, comentarios, ranking);
    }
}