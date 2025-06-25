package com.example.lab_web.Repository; // No pacote test/java/com/example/lab_web/Repository

import com.example.lab_web.Model.Atualizacao;
import com.example.lab_web.Model.Cliente;
import com.example.lab_web.Model.NivelConfiabilidade;
import com.example.lab_web.Model.Status;
import com.example.lab_web.Model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat; // Usando AssertJ para asserts mais fluidos

@DataJpaTest // Configura um ambiente de teste JPA
class AtualizacaoRepositoryTest {

    @Autowired
    private AtualizacaoRepository atualizacaoRepository;

    @Autowired
    private TestEntityManager entityManager; // Útil para persistir entidades auxiliares diretamente

    @Test
    @Rollback(false)
    void shouldSaveAndFindAtualizacao() {
        Usuario usuario = new Cliente("Joao Teste", "joao@test.com", "pass", "01/01/1990", "123", "Rua X", "456");
        entityManager.persist(usuario); // Persiste o usuário antes de usá-lo na atualização

        Atualizacao atualizacao = new Atualizacao(usuario, Status.CHEIO, NivelConfiabilidade.ALTA);
        Atualizacao savedAtualizacao = atualizacaoRepository.save(atualizacao);

        assertThat(savedAtualizacao).isNotNull();
        assertThat(savedAtualizacao.getId()).isNotNull();
        assertThat(savedAtualizacao.getStatus()).isEqualTo(Status.CHEIO);
        assertThat(savedAtualizacao.getUsuario().getNome()).isEqualTo("Joao Teste");

        Atualizacao foundAtualizacao = atualizacaoRepository.findById(savedAtualizacao.getId()).orElse(null);
        assertThat(foundAtualizacao).isEqualTo(savedAtualizacao);
    }

    // Exemplo de teste para uma consulta customizada (se você tivesse uma)
    // @Test
    // void shouldFindByUsuario() {
    //     Usuario usuario1 = new Cliente("User1", "u1@test.com", "p1", "01/01/90", "1", "R1", "1");
    //     Usuario usuario2 = new Cliente("User2", "u2@test.com", "p2", "02/02/90", "2", "R2", "2");
    //     entityManager.persist(usuario1);
    //     entityManager.persist(usuario2);
    //     
    //     atualizacaoRepository.save(new Atualizacao(usuario1, Status.VAZIO, NivelConfiabilidade.BAIXA));
    //     atualizacaoRepository.save(new Atualizacao(usuario1, Status.POUCO_VAZIO, NivelConfiabilidade.MEDIA));
    //     atualizacaoRepository.save(new Atualizacao(usuario2, Status.CHEIO, NivelConfiabilidade.ALTA));
    //
    //     List<Atualizacao> usuario1Atualizacoes = atualizacaoRepository.findByUsuario(usuario1);
    //     assertThat(usuario1Atualizacoes).hasSize(2);
    //     assertThat(usuario1Atualizacoes).allMatch(a -> a.getUsuario().equals(usuario1));
    // }
}