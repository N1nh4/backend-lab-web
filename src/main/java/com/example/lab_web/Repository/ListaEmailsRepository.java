package com.example.lab_web.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.lab_web.DTO.EmailDTO;
import com.example.lab_web.Model.ListaEmailsNotificacao;

@Repository
public interface ListaEmailsRepository extends JpaRepository<ListaEmailsNotificacao, Long> {

}