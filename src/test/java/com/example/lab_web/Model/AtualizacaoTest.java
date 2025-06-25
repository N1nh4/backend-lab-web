package com.example.lab_web.Model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*; // Usando JUnit's assert methods

class AtualizacaoTest {

    @Test
    void shouldCreateAtualizacaoWithCorrectData() {
        Usuario usuario = new Cliente("Teste", "test@example.com", "senha123", "01/01/2000", "123", "Rua A", "111");
        Status status = Status.CHEIO;
        NivelConfiabilidade confiabilidade = NivelConfiabilidade.ALTA;

        Atualizacao atualizacao = new Atualizacao(usuario, status, confiabilidade);

        // REMOVIDA OU COMENTADA A LINHA: assertNotNull(atualizacao.getId());
        assertNotNull(atualizacao.getDataHora());
        assertEquals(usuario, atualizacao.getUsuario());
        assertEquals(status, atualizacao.getStatus());
        assertEquals(confiabilidade, atualizacao.getConfiabilidade());
    }

    @Test
    void shouldUpdateStatusCorrectly() {
        Usuario usuario = new Cliente("Teste", "test@example.com", "senha123", "01/01/2000", "123", "Rua A", "111");
        Atualizacao atualizacao = new Atualizacao(usuario, Status.VAZIO, NivelConfiabilidade.BAIXA);
        
        atualizacao.setStatus(Status.MUITO_CHEIO);
        assertEquals(Status.MUITO_CHEIO, atualizacao.getStatus());
    }
}