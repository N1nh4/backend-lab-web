package com.example.lab_web.DTO;

import java.time.LocalDateTime;
import java.util.List;

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
    private String nome;
    private int nota;
    private Status status; 
    private Endereco endereco; 
    private String telefone; 
    private String imagemURL; 
    private List<Comentario> comentarios;
    private LocalDateTime ultimaAtualizacao;
   
    public UnidadePaginaDTO(Unidade unidade, InformacoesUnidade informacoesUnidade) {
        
        if (informacoesUnidade != null) {
            this.nome = informacoesUnidade.getNome();
            this.telefone = informacoesUnidade.getTelefone();
            this.imagemURL = informacoesUnidade.getImagemURL();
            this.comentarios = informacoesUnidade.getComentarios();
            this.ultimaAtualizacao = informacoesUnidade.getUltimaAtualizacao();
        }

        if (unidade != null) {
            // O status da Unidade no ERD está em HistoricoAtualizacao. Vamos assumir que você buscará o status mais recente.
            // Se Unidade tivesse um campo 'status' diretamente, seria mais simples.
            // Por enquanto, vamos assumir que 'status' virá da 'ultimaAtualizacao'
            this.status = unidade.getStatus(); 
        }

        if (informacoesUnidade != null && informacoesUnidade.getEndereco() != null) {
            this.endereco = informacoesUnidade.getEndereco();
        }

        this.nota = informacoesUnidade.getNota();
    }
}