package com.example.lab_web.Service; // No pacote test/java/com/example/lab_web/Service

import com.example.lab_web.Model.Administrador;
import com.example.lab_web.Model.Atualizacao;
import com.example.lab_web.Model.Cliente;
import com.example.lab_web.Model.Funcionario;
import com.example.lab_web.Model.HistoricoDeAtualizacao;
import com.example.lab_web.Model.NivelConfiabilidade;
import com.example.lab_web.Model.Status;
import com.example.lab_web.Model.Unidade;
import com.example.lab_web.Model.Usuario;
import com.example.lab_web.Repository.AtualizacaoRepository;
import com.example.lab_web.Repository.UnidadeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class) // Habilita o Mockito para JUnit 5
@MockitoSettings(strictness = Strictness.LENIENT)

class UnidadeServiceTest {

    @Mock // Cria um mock para o repositório de Atualizacao
    private AtualizacaoRepository atualizacaoRepository;

    @Mock // Cria um mock para o repositório de Unidade
    private UnidadeRepository unidadeRepository;

    @InjectMocks // Injeta os mocks criados acima no UnidadeService
    private UnidadeService unidadeService;

    private Unidade mockUnidade;
    private Funcionario mockFuncionario;
    private Cliente mockCliente;

    @BeforeEach
    void setUp() {
        mockUnidade = new Unidade();
        mockUnidade.setId(1L);
        mockUnidade.setStatus(Status.VAZIO);
        
        mockFuncionario = new Funcionario("Func Joao", "func@email.com", "pass", "01/01/1985", "123", "Rua A", "111");
        mockCliente = new Cliente("Cli Maria", "cli@email.com", "pass", "01/01/1990", "456", "Rua B", "222");

        // Configura o comportamento padrão do findById do unidadeRepository
        when(unidadeRepository.findById(1L)).thenReturn(Optional.of(mockUnidade));
        // Configura o comportamento padrão do save
        when(atualizacaoRepository.save(any(Atualizacao.class))).thenAnswer(invocation -> {
            Atualizacao atualizacao = invocation.getArgument(0);
            // Simula a atribuição de um ID (se o ID for gerado pelo banco)
            if (atualizacao.getId() == null) {
                atualizacao.setId(100L); 
            }
            return atualizacao;
        });
        when(unidadeRepository.save(any(Unidade.class))).thenAnswer(invocation -> invocation.getArgument(0));
    }

    @Test
    void shouldRegisterStatusForFuncionarioWithHighConfiabilidade() {
        // Crio um HistoricoDeAtualizacao para a mockUnidade (simulando que ela já tenha um)
        HistoricoDeAtualizacao historico = new HistoricoDeAtualizacao(mockUnidade, new ArrayList<>());
        mockUnidade.setHistoricoDeAtualizacao(historico);

        // Chamo o método a ser testado
        unidadeService.registrarStatusUnidade(1L, mockFuncionario, Status.CHEIO);

        // Verificações
        // 1. UnidadeRepository.findById foi chamado uma vez com o ID correto
        verify(unidadeRepository, times(1)).findById(1L);
        // 2. AtualizacaoRepository.save foi chamado uma vez
        verify(atualizacaoRepository, times(1)).save(any(Atualizacao.class));
        // 3. UnidadeRepository.save foi chamado uma vez
        verify(unidadeRepository, times(1)).save(mockUnidade);

        // Verifica se o status da unidade foi atualizado
        assertEquals(Status.CHEIO, mockUnidade.getStatus());
        // Verifica se a atualização foi adicionada ao histórico
        assertThat(mockUnidade.getHistoricoDeAtualizacao().getAtualizacoes()).hasSize(1);
        // Verifica a confiabilidade da atualização criada (que depende da fábrica)
        assertEquals(NivelConfiabilidade.ALTA, mockUnidade.getHistoricoDeAtualizacao().getAtualizacoes().get(0).getConfiabilidade());
        assertEquals(mockFuncionario, mockUnidade.getHistoricoDeAtualizacao().getAtualizacoes().get(0).getUsuario());
    }

    @Test
    void shouldRegisterStatusForClienteWithLowConfiabilidadeAndCreateNewHistorico() {
        // Garante que a unidade não tem histórico no início do teste
        mockUnidade.setHistoricoDeAtualizacao(null); 

        unidadeService.registrarStatusUnidade(1L, mockCliente, Status.VAZIO);

        verify(atualizacaoRepository, times(1)).save(any(Atualizacao.class));
        verify(unidadeRepository, times(1)).save(mockUnidade); // Unidade é salva duas vezes (uma para setHistorico, outra para status)

        assertEquals(Status.VAZIO, mockUnidade.getStatus());
        assertThat(mockUnidade.getHistoricoDeAtualizacao()).isNotNull();
        assertThat(mockUnidade.getHistoricoDeAtualizacao().getAtualizacoes()).hasSize(1);
        assertEquals(NivelConfiabilidade.BAIXA, mockUnidade.getHistoricoDeAtualizacao().getAtualizacoes().get(0).getConfiabilidade());
        assertEquals(mockCliente, mockUnidade.getHistoricoDeAtualizacao().getAtualizacoes().get(0).getUsuario());
    }

    @Test
    void shouldThrowExceptionWhenUnidadeNotFound() {
        // Configura o findById para retornar vazio para um ID diferente
        when(unidadeRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            unidadeService.registrarStatusUnidade(99L, mockFuncionario, Status.CHEIO);
        });

        assertEquals("Unidade não encontrada!", thrown.getMessage());
        // Garante que nenhum save foi chamado
        verify(atualizacaoRepository, never()).save(any(Atualizacao.class));
        verify(unidadeRepository, never()).save(any(Unidade.class));
    }

    @Test
    void shouldThrowExceptionWhenUnauthorizedUserRegistersStatus() {
        // Criar um usuário que não seja nem Funcionario nem Cliente (ex: um Administrador, se ele não tiver permissão para isso)
        Usuario usuarioInvalido = new Administrador("Admin Teste", "admin@email.com", "pass", "01/01/1980", "789", "Rua D", "444");

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            unidadeService.registrarStatusUnidade(1L, usuarioInvalido, Status.CHEIO);
        });

        assertEquals("Usuário do tipo Administrador não autorizado a registrar status da unidade.", thrown.getMessage());
        verify(atualizacaoRepository, never()).save(any(Atualizacao.class));
        verify(unidadeRepository, never()).save(any(Unidade.class));
    }
}