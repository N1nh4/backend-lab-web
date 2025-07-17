package com.example.lab_web.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lab_web.DTO.UnidadeMapaDTO;
import com.example.lab_web.Model.InformacoesUnidade;
import com.example.lab_web.Model.Unidade;
import com.example.lab_web.Repository.InformacoesUnidadeRepository;
import com.example.lab_web.Repository.UnidadeMapaRepository;

@Service
public class UnidadeMapaService {

    @Autowired
    private UnidadeMapaRepository unidadeMapaRepository;

    @Autowired
    private InformacoesUnidadeRepository informacoesUnidadeRepository;

    public List<UnidadeMapaDTO> listarTodas() {
        List<Unidade> unidades = unidadeMapaRepository.findAll();

        return unidades.stream()
                .map(unidade -> {
                    InformacoesUnidade info = informacoesUnidadeRepository.findByUnidade(unidade)
                        .orElseThrow(() -> new RuntimeException(
                            "Informações não encontradas para unidade id: " + unidade.getId()));
    
                    return new UnidadeMapaDTO(
                        info.getNome(),
                        info.getLat(),
                        info.getLng(),
                        unidade.getStatus().name()
                    );
                })
                .collect(Collectors.toList());
    }
    
}
