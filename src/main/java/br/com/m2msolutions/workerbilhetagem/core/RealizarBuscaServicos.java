package br.com.m2msolutions.workerbilhetagem.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.m2msolutions.workerbilhetagem.service.BuscaServicosService;

@Component
public class RealizarBuscaServicos {
	@Autowired
	private BuscaServicosService buscaServicosService;

	@Scheduled(fixedRateString = "${schedule.timer}")
	public void realizarBuscaServicos() {
		buscaServicosService.buscarServicos(2, "P", "170210");
	}
}
