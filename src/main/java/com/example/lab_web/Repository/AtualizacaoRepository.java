package com.example.lab_web.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lab_web.Model.Atualizacao;

@Repository
public interface AtualizacaoRepository extends JpaRepository<Atualizacao, Long> {
    List<Atualizacao> findByUnidadeIdAndDataHoraAfter(Long unidadeId, LocalDateTime dataHora);
}
