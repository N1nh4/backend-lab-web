package com.example.lab_web.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.lab_web.Model.Status;
import com.example.lab_web.Model.Unidade;

public interface UnidadeRepository extends JpaRepository<Unidade, Long> {
    @Query(value = "SELECT status_atual FROM unidade WHERE id = :id", nativeQuery = true)
    Status buscarStatusUnidade(@Param("id") Long id);
}