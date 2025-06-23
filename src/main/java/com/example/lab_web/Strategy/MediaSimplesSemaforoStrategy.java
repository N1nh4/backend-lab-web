package com.example.lab_web.Strategy;

import com.example.lab_web.Model.Avaliacao;
import com.example.lab_web.Model.SemaforoStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("mediaSimplesSemaforoStrategy")
public class MediaSimplesSemaforoStrategy implements CalculoSemaforoStrategy {

	@Override
	public SemaforoStatus calcularSemaforo(List<Avaliacao> avaliacoes) {
		if (avaliacoes == null || avaliacoes.isEmpty()) {
			return SemaforoStatus.INDETERMINADO;
		}

		double somaNotas = avaliacoes.stream()
				.mapToInt(Avaliacao::getNota)
				.average()
				.orElse(0.0);

		if (somaNotas <= 2.5) {
			return SemaforoStatus.VERMELHO;
		} else if (somaNotas <= 3.5) {
			return SemaforoStatus.AMARELO;
		} else {
			return SemaforoStatus.VERDE;
		}
	}

	@Override
	public String getNomeEstrategia() {
		return "MediaSimples";
	}
}
