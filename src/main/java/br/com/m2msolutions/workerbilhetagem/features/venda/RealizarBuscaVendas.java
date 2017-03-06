package br.com.m2msolutions.workerbilhetagem.features.venda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import br.com.m2msolutions.workerbilhetagem.features.cliente.ClienteRjConsultores;
import br.com.m2msolutions.workerbilhetagem.features.venda.model.ListaVendas;
import br.com.m2msolutions.workerbilhetagem.features.venda.service.BuscaVendas;
import br.com.m2msolutions.workerbilhetagem.features.venda.service.EnviaDadosAntt;
import br.com.m2msolutions.workerbilhetagem.features.venda.util.VendasUtil;

@Component
public class RealizarBuscaVendas {
	private Logger LOGGER = LoggerFactory.getLogger(RealizarBuscaVendas.class);

	@Autowired
	private BuscaVendas buscaVendasService;

	@Autowired
	private EnviaDadosAntt enviaDadosAntt;

	@Autowired
	private VendasUtil vendasUtil;

	@Async
	public void realizarBuscaVendas(ClienteRjConsultores clienteRj) throws InterruptedException {
		String url = vendasUtil.createUrl(clienteRj);

		if (!"".equals(url)) {
			ListaVendas listaVendas = buscaVendasService.buscarVendas(url, clienteRj);
			if (listaVendas != null) {
				enviaDadosAntt.enviar(listaVendas, clienteRj);
			}
		} else {
			LOGGER.error("{} - URL Incorreta para consulta", url);
		}
	}
}
