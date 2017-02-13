package br.com.m2msolutions.workerbilhetagem.features.venda;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import br.com.m2msolutions.workerbilhetagem.features.cliente.ClienteRjConsultores;

@Component
public class RealizarBuscaVendas {
	private Logger LOGGER = LoggerFactory.getLogger(BuscaVendasService.class);

	@Autowired
	private BuscaVendasService buscaVendasService;

	@Async
	public void realizarBuscaVendas(ClienteRjConsultores clienteRj) {
		Integer codConexao = Integer.parseInt(clienteRj.getCodConexao());
		String codEmpresa = clienteRj.getCodCliente();
		String data = "";
		String hora = "";

		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			DateFormat newDateFormat = new SimpleDateFormat("yyMMdd");
			DateFormat hourFormat = new SimpleDateFormat("HHmm");

			Date d1 = dateFormat.parse(clienteRj.getDataEnvio());

			data = newDateFormat.format(d1);
			hora = hourFormat.format(d1);

		} catch (Exception e) {
			LOGGER.error("Error - " + e.toString());
		}
		buscaVendasService.buscarVendas(codConexao, codEmpresa, data, hora);
	}
}
