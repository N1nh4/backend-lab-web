package com.example.lab_web.Controller;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lab_web.DTO.UnidadeDTO;
import com.example.lab_web.DTO.UnidadePaginaDTO;
import com.example.lab_web.Model.Avaliacao;
import com.example.lab_web.Model.Cliente;
import com.example.lab_web.Model.Comentario;
import com.example.lab_web.Model.InformacoesUnidade;
import com.example.lab_web.Repository.AvaliacaoRepository;
import com.example.lab_web.Repository.ClienteRepository;
import com.example.lab_web.Repository.ComentarioRepository;
import com.example.lab_web.Repository.InformacoesUnidadeRepository;
import com.example.lab_web.Service.ClienteService;
import com.example.lab_web.Service.UnidadeService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class UnidadeController {

    private UnidadeService us;
    private ClienteService cs;
    private ComentarioRepository comentarioRepository;
    private ClienteRepository clienteRepository;
    private InformacoesUnidadeRepository informacoesUnidadeRepository;
    private AvaliacaoRepository avaliacaoRepository;

    public UnidadeController(UnidadeService us, ClienteService cs, ComentarioRepository comentarioRepository, ClienteRepository clienteRepository, InformacoesUnidadeRepository informacoesUnidadeRepository, AvaliacaoRepository avaliacaoRepository) {
        this.us = us;
        this.cs = cs;
        this.comentarioRepository = comentarioRepository;
        this.clienteRepository = clienteRepository;
        this.informacoesUnidadeRepository = informacoesUnidadeRepository;
        this.avaliacaoRepository = avaliacaoRepository;
    }

    @GetMapping
    public ResponseEntity<List<UnidadeDTO>> getUnidades() {
        List<UnidadeDTO> unidades =  us.getUnidades();
        return ResponseEntity.ok().body(unidades);
    }

    @GetMapping("/unidade/{id}")
    public ResponseEntity<UnidadePaginaDTO> getUnidade(@PathVariable Long id) {
        UnidadePaginaDTO unidade =  us.getUnidade(id);
        return ResponseEntity.ok().body(unidade);
    }

    @PostMapping("/unidade/{id}/registrar-lotacao")
    public ResponseEntity<String> registrarLotacao(@RequestBody AtualizacaoDTO atualizacaoCliente, @PathVariable Long id) {
        System.out.println("Funcionando");
        System.out.println(atualizacaoCliente);
        cs.incrementarContribuicao((long)atualizacaoCliente.getIdUsuario());
        return ResponseEntity.status(HttpStatus.CREATED).body("Avaliação salva!");
    }

    @PostMapping("/unidade/{id}/comentario")
    public ResponseEntity<?> criarComentario(@PathVariable Long id, @RequestBody ComentarioDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getClienteId()).orElse(null);
        if (cliente == null) {
            return ResponseEntity.badRequest().body("Cliente não encontrado");
        }

        InformacoesUnidade informacoesUnidade = informacoesUnidadeRepository.buscarInformacoesUnidadePorIdUnidade(id);
        if (informacoesUnidade == null) {
            return ResponseEntity.badRequest().body("Unidade não encontrada");
        }

        Comentario comentario = new Comentario(cliente, dto.getTexto(), LocalDateTime.now(), informacoesUnidade);
        comentarioRepository.save(comentario);

        return ResponseEntity.status(HttpStatus.CREATED).body("Comentário adicionado com sucesso!");
    }

    @PutMapping("/unidade/{id}/comentario/{comentarioId}")
    public ResponseEntity<?> editarComentario(
            @PathVariable Long id,
            @PathVariable Long comentarioId,
            @RequestBody ComentarioDTO dto) {

        Cliente cliente = clienteRepository.findById(dto.getClienteId()).orElse(null);
        if (cliente == null) {
            return ResponseEntity.badRequest().body("Cliente não encontrado");
        }

        Comentario comentario = comentarioRepository.findById(comentarioId).orElse(null);
        if (comentario == null) {
            return ResponseEntity.badRequest().body("Comentário não encontrado");
        }

        if (!comentario.getCliente().getId().equals(dto.getClienteId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Você só pode editar seus próprios comentários");
        }

        comentario.setTexto(dto.getTexto());
        comentarioRepository.save(comentario);

        return ResponseEntity.ok("Comentário atualizado com sucesso!");
    }

    @DeleteMapping("/unidade/{id}/comentario/{comentarioId}")
    public ResponseEntity<?> excluirComentario(
            @PathVariable Long id,
            @PathVariable Long comentarioId,
            @RequestBody ComentarioDTO dto) {

        Cliente cliente = clienteRepository.findById(dto.getClienteId()).orElse(null);
        if (cliente == null) {
            return ResponseEntity.badRequest().body("Cliente não encontrado");
        }

        Comentario comentario = comentarioRepository.findById(comentarioId).orElse(null);
        if (comentario == null) {
            return ResponseEntity.badRequest().body("Comentário não encontrado");
        }

        if (!comentario.getCliente().getId().equals(dto.getClienteId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Você só pode excluir seus próprios comentários");
        }

        comentarioRepository.delete(comentario);

        return ResponseEntity.ok("Comentário excluído com sucesso!");
    }

    @PostMapping("/unidade/{id}/avaliacao")
    public ResponseEntity<?> criarAvaliacao(@PathVariable Long id, @RequestBody AvaliacaoDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getClienteId()).orElse(null);
        if (cliente == null) {
            return ResponseEntity.badRequest().body("Cliente não encontrado");
        }

        InformacoesUnidade informacoesUnidade = informacoesUnidadeRepository.buscarInformacoesUnidadePorIdUnidade(id);
        if (informacoesUnidade == null) {
            return ResponseEntity.badRequest().body("Unidade não encontrada");
        }

        if (dto.getNota() < 1 || dto.getNota() > 5) {
            return ResponseEntity.badRequest().body("Nota deve ser entre 1 e 5");
        }

        var avaliacaoExistente = avaliacaoRepository.findByClienteIdAndInformacoesUnidadeId(dto.getClienteId(), informacoesUnidade.getId());
        if (avaliacaoExistente.isPresent()) {
            avaliacaoExistente.get().setNota(dto.getNota());
            avaliacaoExistente.get().setDataHora(LocalDateTime.now());
            avaliacaoRepository.save(avaliacaoExistente.get());
        } else {
            Avaliacao avaliacao = new Avaliacao(cliente, informacoesUnidade, dto.getNota());
            avaliacaoRepository.save(avaliacao);
        }

        double media = avaliacaoRepository.calcularMediaNota(informacoesUnidade.getId());
        informacoesUnidade.setNota((int) Math.round(media));
        informacoesUnidadeRepository.save(informacoesUnidade);

        return ResponseEntity.status(HttpStatus.CREATED).body("Avaliação salva com sucesso!");
    }
}
