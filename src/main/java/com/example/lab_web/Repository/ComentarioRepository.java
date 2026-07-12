package com.example.lab_web.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.lab_web.Model.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    @Query("SELECT c FROM Comentario c WHERE c.informacoesUnidade.unidade.id = :idUnidade")
    Iterable<Comentario> buscarComentariosPorUnidade(@Param("idUnidade") Long idUnidade);

    void deleteByClienteId(Long clienteId);
}
