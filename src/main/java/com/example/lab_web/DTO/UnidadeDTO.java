package com.example.lab_web.DTO;

import com.example.lab_web.Model.Atualizacao;
import com.example.lab_web.Model.Avaliacao;
import com.example.lab_web.Model.Endereco;
import com.example.lab_web.Model.Status;
import com.example.lab_web.Model.Unidade; 
import com.example.lab_web.Model.InformacoesUnidade; 

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnidadeDTO {
    private String nome;
    private int nota;
    private Status status; 
    private Endereco endereco; 
    private String telefone; 
    private String imagemURL; 
    private Atualizacao ultimaAtualizacao; 
   
    public UnidadeDTO(Unidade unidade, InformacoesUnidade informacoesUnidade, Atualizacao ultimaAtualizacao, int nota) {
        
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
        
        if (ultimaAtualizacao != null && ultimaAtualizacao.getStatus() != null) {
            this.status = ultimaAtualizacao.getStatus();
        }

        if (informacoesUnidade != null && informacoesUnidade.getEndereco() != null) {
            this.endereco = informacoesUnidade.getEndereco();
        }

        this.nota = nota;

        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    public UnidadeDTO(String nome, Status status, Endereco endereco, String telefone, String imagemURL, Atualizacao ultimaAtualizacao, Avaliacao avaliacao) {
        this.nome = nome;
        this.status = status;
        this.endereco = endereco;
        this.telefone = telefone;
        this.imagemURL = imagemURL;
        this.ultimaAtualizacao = ultimaAtualizacao;
    }
}