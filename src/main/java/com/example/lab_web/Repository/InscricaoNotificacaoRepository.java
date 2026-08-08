package com.example.lab_web.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lab_web.Model.InscricaoNotificacao;

@Repository
public interface InscricaoNotificacaoRepository extends JpaRepository<InscricaoNotificacao, Long> {

    List<InscricaoNotificacao> findByUnidadeId(Long unidadeId);

    List<InscricaoNotificacao> findByEmail(String email);

    Optional<InscricaoNotificacao> findByEmailAndUnidadeId(String email, Long unidadeId);

    void deleteByEmailAndUnidadeId(String email, Long unidadeId);
}
