package com.example.lab_web.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.lab_web.DTO.UsuarioDTO;
import com.example.lab_web.Model.Cliente;
import com.example.lab_web.Model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
   
    Usuario findByEmail(String email);

}
