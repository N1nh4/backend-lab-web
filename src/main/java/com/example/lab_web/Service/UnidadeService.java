package com.example.lab_web.Service;

import com.example.lab_web.Model.Unidade;
import com.example.lab_web.Repository.UnidadeRepository;

public class UnidadeService {

    private UnidadeRepository ur;

    public Unidade buscar(Long id) {
        return ur.findById(id).get();
    }
}
