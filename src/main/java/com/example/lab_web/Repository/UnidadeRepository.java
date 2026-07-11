package com.example.lab_web.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.lab_web.Model.Unidade;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Long> {

    @Query("SELECT u FROM Unidade u LEFT JOIN FETCH u.historicoDeAtualizacao")
    List<Unidade> findAllWithHistorico();
}
