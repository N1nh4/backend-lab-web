package com.example.lab_web.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.lab_web.DTO.EmailDTO;
import com.example.lab_web.Model.ListaEmailsNotificacao;
import com.example.lab_web.Model.Status;
import com.example.lab_web.Model.Unidade;
import com.example.lab_web.Repository.ListaEmailsRepository;
import com.example.lab_web.Repository.UnidadeRepository;

@Service
public class ListaEmailsService {

    private final ListaEmailsRepository ler;
    private final UnidadeRepository ur;
    private final EmailService es;
    
    public ListaEmailsService(ListaEmailsRepository ler, UnidadeRepository ur, EmailService es) {
        this.ler = ler;
        this.ur = ur;
        this.es = es;
    }

    public List<ListaEmailsNotificacao> getEmails() {
        return ler.findAll();
    }
    
    public String salvarEmail(EmailDTO email) {

        ListaEmailsNotificacao emailSalvo = new ListaEmailsNotificacao();
        emailSalvo.setEmail(email.getEmail());
        ler.save(emailSalvo);
        
        return "Erro ao salvar o email.";
    }
}