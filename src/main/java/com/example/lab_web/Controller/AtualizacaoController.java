package com.example.lab_web.Controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.lab_web.DTO.AtualizacaoRequestDTO;
import com.example.lab_web.DTO.AtualizacaoResponseDTO;
import com.example.lab_web.Model.Atualizacao;
import com.example.lab_web.Model.Status;
import com.example.lab_web.Model.Unidade;
import com.example.lab_web.Model.Usuario;
import com.example.lab_web.Repository.UnidadeRepository;
import com.example.lab_web.Repository.UsuarioRepository;
import com.example.lab_web.Service.AtualizacaoService;
import com.example.lab_web.Service.UnidadeService;

@RestController
@RequestMapping("/atualizacoes")
public class AtualizacaoController {

    private final AtualizacaoService atualizacaoService;
    private final UnidadeService unidadeService;
    private final UsuarioRepository usuarioRepository;
    private final UnidadeRepository unidadeRepository;

    public AtualizacaoController(AtualizacaoService atualizacaoService,
                                 UnidadeService unidadeService,
                                 UsuarioRepository usuarioRepository,
                                 UnidadeRepository unidadeRepository) {
        this.atualizacaoService = atualizacaoService;
        this.unidadeService = unidadeService;
        this.usuarioRepository = usuarioRepository;
        this.unidadeRepository = unidadeRepository;
    }

    // Endpoint POST para criar atualização e recalcular status da unidade
    @PostMapping
    public ResponseEntity<AtualizacaoResponseDTO> criarAtualizacao(@RequestBody AtualizacaoRequestDTO dto) {

        System.out.println("Recebido no backend: status=" + dto.getStatus() + ", usuarioId=" + dto.getUsuarioId() + ", unidadeId=" + dto.getUnidadeId());
        // Buscar usuário e unidade no banco
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        System.out.println("USUARIO ID" + usuario.getId());

        Unidade unidade = unidadeRepository.findById(dto.getUnidadeId())
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada"));

        // Criar atualização com confiabilidade correta pela factory
        Atualizacao atualizacao = atualizacaoService.registrar(dto.getStatus(), usuario, unidade);

        // Recalcular e atualizar o status da unidade
        unidadeService.gerarStatusAtual(unidade.getId());

        // Buscar novamente a unidade atualizada para pegar o status atualizado
        Unidade unidadeAtualizada = unidadeRepository.findById(unidade.getId())
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada"));

        // Preparar DTO de resposta com atualização e status atualizado da unidade
        AtualizacaoResponseDTO response = new AtualizacaoResponseDTO(atualizacao, unidadeAtualizada.getStatus());

        System.out.println("Resposta criada no backend: " + response.getStatusUnidadeAtualizado());

        return ResponseEntity.ok(response);
    }
}
