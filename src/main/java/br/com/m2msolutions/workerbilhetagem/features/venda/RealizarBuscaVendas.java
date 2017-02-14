package br.com.m2msolutions.workerbilhetagem.features.venda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import br.com.m2msolutions.workerbilhetagem.features.cliente.ClienteRjConsultores;

@Component
public class RealizarBuscaVendas {
	private Logger LOGGER = LoggerFactory.getLogger(RealizarBuscaVendas.class);

	@Autowired
	private BuscaVendasService buscaVendasService;

	@Autowired
	private EnviaDadosAntt enviaDadosAntt;

	@Autowired
	private EnviaDadosRabbit enviaDadosRabbit;

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
