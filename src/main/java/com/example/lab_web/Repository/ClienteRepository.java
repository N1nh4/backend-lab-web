package com.example.lab_web.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.lab_web.Model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Modifying
    @Query("UPDATE Cliente c SET c.contribuicoes = c.contribuicoes + 1 WHERE c.id = :id")
    void incrementarContribuicao(@Param("id") Long id);

    Optional<Cliente> findByEmail(String email);

    List<Cliente> findTop10ByOrderByContribuicoesDesc();

}
