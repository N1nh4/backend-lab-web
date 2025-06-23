package com.example.lab_web.service;

import com.example.lab_web.Model.Avaliacao;
import com.example.lab_web.Model.InformacoesUnidade;
import com.example.lab_web.Model.SemaforoStatus;
import com.example.lab_web.Model.Unidade;
import com.example.lab_web.strategy.CalculoSemaforoStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SemaforoService {

	private final Map<String, CalculoSemaforoStrategy> strategies;

	@Autowired
	public SemaforoService(List<CalculoSemaforoStrategy> strategiesList) {
		this.strategies = strategiesList.stream()
				.collect(Collectors.toMap(CalculoSemaforoStrategy::getNomeEstrategia, Function.identity()));
		System.out.println("Estratégias de Semáforo carregadas no SemaforoService: " + this.strategies.keySet());
	}

	public SemaforoStatus calcularEAtualizarSemaforoUnidade(Unidade unidade, String nomeEstrategia) {
		CalculoSemaforoStrategy strategy = strategies.get(nomeEstrategia);
		if (strategy == null) {
			throw new IllegalArgumentException("Estratégia de semáforo não encontrada: " + nomeEstrategia);
		}

		InformacoesUnidade infoUnidade = unidade.getInformacoesUnidade();
		if (infoUnidade == null || infoUnidade.getAvaliacao() == null) {
			unidade.setSemaforoAtual(SemaforoStatus.INDETERMINADO);
			return SemaforoStatus.INDETERMINADO;
		}

		List<Avaliacao> avaliacoes = infoUnidade.getAvaliacao();

		SemaforoStatus novoSemaforo = strategy.calcularSemaforo(avaliacoes);

		unidade.setSemaforoAtual(novoSemaforo);

		return novoSemaforo;
	}

	public List<String> getNomesEstrategiasDisponiveis() {
		return strategies.keySet().stream().sorted().collect(Collectors.toList());
	}
}
