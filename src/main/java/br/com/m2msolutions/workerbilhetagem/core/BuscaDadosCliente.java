package br.com.m2msolutions.workerbilhetagem.core;

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

	@Autowired
	public BuscaDadosCliente(ClienteRjConsultoresRepository clienteRjConsultoresRepository,
			RealizarBuscaVendas realizarBuscaVendas) {
		this.clienteRjConsultoresRepository = clienteRjConsultoresRepository;
		this.realizarBuscaVendas = realizarBuscaVendas;
	}

	@Scheduled(fixedRateString = "${schedule.timer}")
	public void buscarDadosClientes() {
		try {
			for (ClienteRjConsultores clienteRj : clienteRjConsultoresRepository.findAll()) {
				LOGGER.info("Cliente: {} - Cod Conexao: {} - Cod Empresa: {} - Ultima Consulta: {}",
						clienteRj.getCliente().getNmNome(), clienteRj.getCodConexao(), clienteRj.getCodCliente(),
						clienteRj.getDataEnvio());
				realizarBuscaVendas.realizarBuscaVendas(clienteRj);
			}
		} catch (Exception e) {
			LOGGER.error("Error {} ", e.toString());
		}
	}
}
