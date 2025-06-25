package com.example.lab_web.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lab_web.Model.Status;
import com.example.lab_web.Model.Unidade;
import com.example.lab_web.Repository.UnidadeRepository;

@Service
public class UnidadeService {

    @Autowired
    private UnidadeRepository ur;

    public Unidade buscar(Long id) {
        return ur.findById(id).get();
    }

    public Status buscarStatusUnidade(Long id) {
        return ur.buscarStatusUnidade(id);
    }
}
