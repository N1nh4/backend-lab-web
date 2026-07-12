package com.example.lab_web.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.lab_web.Model.Avaliacao;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    @Query("SELECT a FROM Avaliacao a WHERE a.id = :id")
    Optional<Avaliacao> buscarAvalicao(@Param("id") Long id);

    List<Avaliacao> findByInformacoesUnidadeId(Long informacoesUnidadeId);

    Optional<Avaliacao> findByClienteIdAndInformacoesUnidadeId(Long clienteId, Long informacoesUnidadeId);

    void deleteByClienteId(Long clienteId);

    @Query("SELECT COALESCE(AVG(a.nota), 0) FROM Avaliacao a WHERE a.informacoesUnidade.id = :informacoesUnidadeId")
    double calcularMediaNota(@Param("informacoesUnidadeId") Long informacoesUnidadeId);
}
