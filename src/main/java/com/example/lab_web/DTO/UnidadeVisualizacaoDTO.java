package com.example.lab_web.DTO;

import java.util.List;
import java.util.Optional;

import com.example.lab_web.Model.Atualizacao;
import com.example.lab_web.Model.Avaliacao;
import com.example.lab_web.Model.Comentario;
import com.example.lab_web.Model.Endereco;
import com.example.lab_web.Model.Status;
import com.example.lab_web.Model.Unidade; 
import com.example.lab_web.Model.InformacoesUnidade; 

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnidadeVisualizacaoDTO {
    private Long id; 
    private String nome;
    private Unidade unidade;
    private int nota;
    private Status status; 
    private Endereco endereco; 
    private String telefone; 
    private String imagemURL; 
    private List<Comentario> comentarios; 
   
    public UnidadeVisualizacaoDTO(Long id, Unidade unidade, InformacoesUnidade informacoesUnidade, int nota) {

        this.id = id;
        this.comentarios = informacoesUnidade.getComentarios();

        if (informacoesUnidade != null) {
            this.nome = informacoesUnidade.getNome();
            this.telefone = informacoesUnidade.getTelefone();
            this.imagemURL = informacoesUnidade.getImagemURL(); 
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

        this.nota = nota;
    }
}