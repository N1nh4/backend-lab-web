package com.example.lab_web.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lab_web.Model.Cliente;
import com.example.lab_web.Model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
}
