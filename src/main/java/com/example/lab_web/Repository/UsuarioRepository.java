package com.example.lab_web.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lab_web.Model.Cliente;

public interface UsuarioRepository extends JpaRepository<Cliente, Long> {
    
}
