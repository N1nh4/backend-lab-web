package com.example.lab_web.Strategy;

import com.example.lab_web.Model.Avaliacao;
import com.example.lab_web.Model.SemaforoStatus;

import java.util.List;

public interface CalculoSemaforoStrategy {
	SemaforoStatus calcularSemaforo(List<Avaliacao> avaliacoes);

	String getNomeEstrategia();
}
