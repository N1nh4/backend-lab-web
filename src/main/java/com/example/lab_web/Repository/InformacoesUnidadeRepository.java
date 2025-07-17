package com.example.lab_web.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.lab_web.Model.InformacoesUnidade;
import com.example.lab_web.Model.Unidade;

@Repository
public interface InformacoesUnidadeRepository extends JpaRepository<InformacoesUnidade, Long> {

    @Query("SELECT i.nota FROM InformacoesUnidade i WHERE i.id = :id")
    int getNota(@Param("id") Long id);

    @Query("SELECT i FROM InformacoesUnidade i WHERE i.unidade.id = :idUnidade")
    InformacoesUnidade buscarInformacoesUnidadePorIdUnidade(@Param("idUnidade") Long idUnidade);

    Optional<InformacoesUnidade> findByUnidade(Unidade unidade);
}
