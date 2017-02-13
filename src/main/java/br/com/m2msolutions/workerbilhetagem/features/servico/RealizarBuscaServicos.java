package br.com.m2msolutions.workerbilhetagem.features.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RealizarBuscaServicos {
	@Autowired
	private BuscaServicosService buscaServicosService;

	public void realizarBuscaServicos() {
		buscaServicosService.buscarServicos(2, "P", "170210");
	}
}
