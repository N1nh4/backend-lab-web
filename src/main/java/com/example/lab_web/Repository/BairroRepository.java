package com.example.lab_web.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lab_web.Model.Bairro;

@Repository
public interface BairroRepository extends JpaRepository<Bairro, Long> {
    
}
