package com.example.lab_web.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lab_web.Model.InformacoesUnidade;
import com.example.lab_web.Model.Unidade;

@Repository
public interface UnidadeMapaRepository extends JpaRepository<Unidade, Long>{

  
} 
