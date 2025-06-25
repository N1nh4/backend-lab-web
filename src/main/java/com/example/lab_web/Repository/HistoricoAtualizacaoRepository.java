package com.example.lab_web.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lab_web.Model.HistoricoDeAtualizacao;

public interface HistoricoAtualizacaoRepository extends JpaRepository<HistoricoDeAtualizacao, Long> {
    
}
