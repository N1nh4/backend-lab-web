package com.example.lab_web.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.lab_web.Model.Avaliacao;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    @Query(value = "SELECT comentario FROM avaliacao WHERE id_info_unidade = :idUnidade", nativeQuery = true)
    List<String> buscarComentariosPorIdUnidade(@Param("idUnidade") Long id);

}
