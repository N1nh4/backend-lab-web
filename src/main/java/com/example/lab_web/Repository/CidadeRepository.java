package com.example.lab_web.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lab_web.Model.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    
}
