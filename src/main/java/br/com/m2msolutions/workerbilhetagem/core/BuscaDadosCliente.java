package br.com.m2msolutions.workerbilhetagem.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.m2msolutions.workerbilhetagem.features.cliente.ClienteRjConsultores;
import br.com.m2msolutions.workerbilhetagem.features.cliente.ClienteRjConsultoresRepository;
import br.com.m2msolutions.workerbilhetagem.features.venda.BuscaVendasService;
import br.com.m2msolutions.workerbilhetagem.features.venda.RealizarBuscaVendas;

@Component
public class BuscaDadosCliente {
	private Logger LOGGER = LoggerFactory.getLogger(BuscaVendasService.class);

	private final ClienteRjConsultoresRepository clienteRjConsultoresRepository;
	private RealizarBuscaVendas realizarBuscaVendas;

	@Autowired
	public BuscaDadosCliente(ClienteRjConsultoresRepository clienteRjConsultoresRepository,
			RealizarBuscaVendas realizarBuscaVendas) {
		this.clienteRjConsultoresRepository = clienteRjConsultoresRepository;
		this.realizarBuscaVendas = realizarBuscaVendas;

		buscarDadosClientes();
	}

	public void buscarDadosClientes() {
		try {
			for (ClienteRjConsultores clienteRj : clienteRjConsultoresRepository.findAll()) {
				realizarBuscaVendas.realizarBuscaVendas(clienteRj);
			}
		} catch (Exception e) {
			LOGGER.error("Error - " + e.toString());
		}
	}

}
