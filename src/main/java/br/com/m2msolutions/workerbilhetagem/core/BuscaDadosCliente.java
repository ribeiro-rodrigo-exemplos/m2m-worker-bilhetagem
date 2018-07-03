package br.com.m2msolutions.workerbilhetagem.core;

import br.com.m2msolutions.workerbilhetagem.config.Config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.m2msolutions.workerbilhetagem.features.cliente.ClienteRjConsultores;
import br.com.m2msolutions.workerbilhetagem.features.cliente.ClienteRjConsultoresRepository;
import br.com.m2msolutions.workerbilhetagem.features.venda.RealizarBuscaVendas;

@Component
public class BuscaDadosCliente {
	private Logger LOGGER = LoggerFactory.getLogger(BuscaDadosCliente.class);

	private final ClienteRjConsultoresRepository clienteRjConsultoresRepository;

	private RealizarBuscaVendas realizarBuscaVendas;
	private Config config;

	@Autowired
	public BuscaDadosCliente(ClienteRjConsultoresRepository clienteRjConsultoresRepository,
			RealizarBuscaVendas realizarBuscaVendas,Config config) {
		this.clienteRjConsultoresRepository = clienteRjConsultoresRepository;
		this.realizarBuscaVendas = realizarBuscaVendas;
		this.config = config;
	}

	@Scheduled(fixedRateString = "${schedule.timer}")
	public void buscarDadosClientes() {
		try {
			 List<ClienteRjConsultores> listaClientes = clienteRjConsultoresRepository.findAllByCliente_UrlZona(config.getUrlZona());
			for (ClienteRjConsultores clienteRj : listaClientes) {
				LOGGER.info("Nome Cliente: {} - Cod Cliente {} - Cod Conexao: {} - Cod Empresa: {} - Ultima Consulta: {}",
						clienteRj.getCliente().getNmNome(), clienteRj.getCliente().getIdCliente(), clienteRj.getCodConexao(), clienteRj.getCodCliente(),
						clienteRj.getDataEnvio());
				LOGGER.info("Consorcio Cliente: {}", clienteRj.getCliente().getListaConsorcioCliente().size());
				realizarBuscaVendas.realizarBuscaVendas(clienteRj);
			}
		} catch (Exception e) {
			LOGGER.error("Error {} ", e.toString());
		}
	}
}
