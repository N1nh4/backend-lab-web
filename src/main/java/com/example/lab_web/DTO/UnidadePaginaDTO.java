package com.example.lab_web.DTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.example.lab_web.Model.Comentario;
import com.example.lab_web.Model.Endereco;
import com.example.lab_web.Model.Status;
import com.example.lab_web.Model.Unidade; 
import com.example.lab_web.Model.InformacoesUnidade; 

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnidadePaginaDTO {
    private Long id;
    private String nome;
    private int nota;
    private Status status;
    private Endereco endereco;
    private String telefone;
    private String imagemURL;
    private List<ComentarioResponseDTO> comentarios;
    private LocalDateTime ultimaAtualizacao;

    public UnidadePaginaDTO(Unidade unidade, InformacoesUnidade informacoesUnidade) {
        if (unidade != null) {
            this.id = unidade.getId();
        }
        
        if (informacoesUnidade != null) {
            this.nome = informacoesUnidade.getNome();
            this.telefone = informacoesUnidade.getTelefone();
            this.imagemURL = informacoesUnidade.getImagemURL();
            this.ultimaAtualizacao = informacoesUnidade.getUltimaAtualizacao();

            if (informacoesUnidade.getComentarios() != null) {
                this.comentarios = informacoesUnidade.getComentarios().stream()
                    .map(c -> new ComentarioResponseDTO(
                        c.getId(),
                        c.getTexto(),
                        c.getData_hora(),
                        c.getCliente().getNome(),
                        c.getCliente().getFotoURL()
                    ))
                    .collect(Collectors.toList());
            }
        }

        if (unidade != null) {
            this.status = unidade.getStatus(); 
        }

        if (informacoesUnidade != null && informacoesUnidade.getEndereco() != null) {
            this.endereco = informacoesUnidade.getEndereco();
        }

        this.nota = informacoesUnidade.getNota();
    }
}