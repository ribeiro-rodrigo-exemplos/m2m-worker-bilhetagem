package br.com.m2msolutions.workerbilhetagem.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.m2msolutions.workerbilhetagem.service.BuscaVendasService;

@Component
public class RealizarBuscaVendas {
	@Autowired
	private BuscaVendasService buscaVendasService;

	@Scheduled(fixedRateString = "${schedule.timer}")
	public void realizarBuscaVendas() {
		buscaVendasService.buscarVendas(2, "P", "170210", "0900");
	}
}
