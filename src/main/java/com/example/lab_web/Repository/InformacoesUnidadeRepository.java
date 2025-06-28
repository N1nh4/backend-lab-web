package com.example.lab_web.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.lab_web.Model.InformacoesUnidade;

@Repository
public interface InformacoesUnidadeRepository extends JpaRepository<InformacoesUnidade, Long> {
    @Query("SELECT i.nota FROM InformacoesUnidade i WHERE i.unidade.id = :id")
    int getNota(Long id);
}
