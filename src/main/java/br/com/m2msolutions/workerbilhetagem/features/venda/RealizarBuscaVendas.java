package br.com.m2msolutions.workerbilhetagem.features.venda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import br.com.m2msolutions.workerbilhetagem.features.cliente.ClienteRjConsultores;
import br.com.m2msolutions.workerbilhetagem.features.venda.model.ListaVendasModel;
import br.com.m2msolutions.workerbilhetagem.features.venda.service.BuscaVendasService;
import br.com.m2msolutions.workerbilhetagem.features.venda.service.EnviaDadosAnttService;
import br.com.m2msolutions.workerbilhetagem.features.venda.service.EnviaDadosRabbitService;
import br.com.m2msolutions.workerbilhetagem.features.venda.util.VendasUtil;

@Component
public class RealizarBuscaVendas {
	private Logger LOGGER = LoggerFactory.getLogger(RealizarBuscaVendas.class);

	@Autowired
	private BuscaVendasService buscaVendasService;

	@Autowired
	private EnviaDadosAnttService enviaDadosAntt;

	@Autowired
	private EnviaDadosRabbitService enviaDadosRabbit;

	@Autowired
	private VendasUtil vendasUtil;

	@Async
	public void realizarBuscaVendas(ClienteRjConsultores clienteRj) {
		String url = vendasUtil.createUrl(clienteRj);
		ListaVendasModel listaVendas = null;

		if (!"".equals(url)) {
			LOGGER.info(url);
			listaVendas = buscaVendasService.buscarVendas(url);
			if (listaVendas != null) {
				enviaDadosAntt.enviar(listaVendas, clienteRj);
				enviaDadosRabbit.enviar(listaVendas, clienteRj);
			}
		} else {
			LOGGER.error(url + " - URL Incorreta para consulta");
		}
	}

}
