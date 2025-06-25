package com.example.lab_web.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lab_web.Repository.AvaliacaoRepository;

@Service
public class AvaliacaoService {
    
    @Autowired
    private AvaliacaoRepository ar;

    public List<String> buscarComentariosPorIdUnidade(Long id) {
        return ar.buscarComentariosPorIdUnidade(id);
    }
}
