package br.com.m2msolutions.workerbilhetagem.features.venda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import br.com.m2msolutions.workerbilhetagem.features.cliente.ClienteRjConsultores;

@Component
public class EnviaDadosRabbit {
	private Logger LOGGER = LoggerFactory.getLogger(EnviaDadosRabbit.class);

	public void enviar(ListaVendasModel listaVendas, ClienteRjConsultores clienteRj) {
		LOGGER.info("Enviar Dados Rabbit");
		LOGGER.info(
				"Cliente: " + clienteRj.getCliente().getNome() + " - Ultima Atualizacao: " + clienteRj.getDataEnvio());

		String json = ParseListaVendasToJson.parse(listaVendas);
		LOGGER.info(json);
	}
}
