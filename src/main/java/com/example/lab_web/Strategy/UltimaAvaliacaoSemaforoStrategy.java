package com.example.lab_web.Strategy;

import com.example.lab_web.Model.Avaliacao;
import com.example.lab_web.Model.SemaforoStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component("ultimaAvaliacaoSemaforoStrategy")
public class UltimaAvaliacaoSemaforoStrategy implements CalculoSemaforoStrategy {

	@Override
	public SemaforoStatus calcularSemaforo(List<Avaliacao> avaliacoes) {
		if (avaliacoes == null || avaliacoes.isEmpty()) {
			return SemaforoStatus.INDETERMINADO;
		}

		Avaliacao maisRecente = avaliacoes.get(avaliacoes.size() - 1);

		switch (maisRecente.getNota()) {
			case 1:
			case 2:
				return SemaforoStatus.VERMELHO;
			case 3:
				return SemaforoStatus.AMARELO;
			case 4:
			case 5:
				return SemaforoStatus.VERDE;
			default:
				return SemaforoStatus.INDETERMINADO;
		}
	}

	@Override
	public String getNomeEstrategia() {
		return "UltimaAvaliacao";
	}
}
