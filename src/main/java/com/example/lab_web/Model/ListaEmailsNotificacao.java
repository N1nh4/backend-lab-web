package com.example.lab_web.Model;

import java.util.ArrayList;
import java.util.List;

import com.example.lab_web.DTO.EmailDTO;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class ListaEmailsNotificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection
    private List<String> emails;

    public ListaEmailsNotificacao() {
        this.emails = new ArrayList<>();
    }

    public void setEmail(String email) {
        this.emails.add(email);
    }
}