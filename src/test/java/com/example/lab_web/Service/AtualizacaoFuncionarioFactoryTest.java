package com.example.lab_web.Service; // No pacote test/java/com/example/lab_web/Service

import com.example.lab_web.Model.Atualizacao;
import com.example.lab_web.Model.Funcionario;
import com.example.lab_web.Model.NivelConfiabilidade;
import com.example.lab_web.Model.Status;
import com.example.lab_web.Model.Cliente; // Para testar casos de usuário incorreto
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class AtualizacaoFuncionarioFactoryTest {

    private AtualizacaoFactory factory = new AtualizacaoFuncionarioFactory();

    @Test
    void shouldCreateAtualizacaoWithHighConfiabilidadeForFuncionario() {
        Funcionario funcionario = new Funcionario("Ana", "ana@empresa.com", "abc", "01/01/1990", "123", "Rua B", "222");
        Status status = Status.POUCO_VAZIO;

        Atualizacao atualizacao = factory.criarAtualizacao(funcionario, status);

        assertThat(atualizacao).isNotNull();
        assertThat(atualizacao.getUsuario()).isEqualTo(funcionario);
        assertThat(atualizacao.getStatus()).isEqualTo(status);
        assertThat(atualizacao.getConfiabilidade()).isEqualTo(NivelConfiabilidade.ALTA);
    }

    @Test
    void shouldCreateAtualizacaoWithMediumConfiabilidadeForNonFuncionario() {
        // Testando o fallback da fábrica se um não-funcionário for passado (embora o service evite isso)
        Cliente cliente = new Cliente("Carlos", "carlos@user.com", "pass", "01/01/1990", "456", "Rua C", "333");
        Status status = Status.MODERADO;

        Atualizacao atualizacao = factory.criarAtualizacao(cliente, status);

        assertThat(atualizacao).isNotNull();
        assertThat(atualizacao.getUsuario()).isEqualTo(cliente);
        assertThat(atualizacao.getStatus()).isEqualTo(status);
        assertThat(atualizacao.getConfiabilidade()).isEqualTo(NivelConfiabilidade.MEDIA); // Média, como fallback
    }
}