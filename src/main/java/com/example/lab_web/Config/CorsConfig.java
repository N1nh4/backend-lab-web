package com.example.lab_web.Config;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.lab_web.DTO.UsuarioDTO;
import com.example.lab_web.Model.Usuario;
import com.example.lab_web.Repository.UsuarioRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Autowired
    UsuarioRepository us;

    private String chaveSecreta = "minhaChaveSecretaJWT"; // Use algo mais seguro em produção

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configurationSource(request -> {
            var corsConfig = new org.springframework.web.cors.CorsConfiguration();
            corsConfig.setAllowedOriginPatterns(List.of("http://localhost:3000"));
            corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
            corsConfig.setAllowedHeaders(List.of("*"));
            corsConfig.setAllowCredentials(true);
            return corsConfig;
        }))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/**").permitAll()
                //.anyRequest().authenticated()
            )
            .csrf(csrf -> csrf.disable())
            .headers(headers -> headers.frameOptions(frame -> frame.disable()));
        
        return http.build();
    }

    public String autenticar(String email, String senha) {
        Usuario usuario = us.findByEmail(email);
        
        if (usuario == null) {
            throw new RuntimeException("Usuário nao encontrado");
        }

        if (!usuario.getSenha().equals(senha)) { // Recomendado usar hash!
            throw new RuntimeException("Senha inválida");
        }

        return gerarTokenJWT(usuario);
    }

    private String gerarTokenJWT(Usuario usuario) {
        return Jwts.builder()
            .setSubject(usuario.getEmail())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 dia
            .signWith(SignatureAlgorithm.HS256, chaveSecreta)
            .compact();
    }
}

