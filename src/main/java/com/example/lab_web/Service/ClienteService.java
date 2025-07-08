package com.example.lab_web.Service;

import org.springframework.stereotype.Service;

import com.example.lab_web.Repository.ClienteRepository;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {
    
    private ClienteRepository cr;

    public ClienteService(ClienteRepository cr) {
        this.cr = cr;
    }

    @Transactional
    public void incrementarContribuicao(Long id) {
        cr.incrementarContribuicao(id);
    }
}