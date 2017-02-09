package br.com.m2msolutions.workerbilhetagem;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.m2msolutions.workerbilhetagem.service.BuscaVendasService;

@Component
public class RealizarBuscaVendas {
	private Logger LOGGER = LoggerFactory.getLogger(BuscaVendasService.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	private BuscaVendasService buscaVendasService;

	@Scheduled(fixedRateString = "${schedule.timer}")
	public void realizarBuscaVendas() {
		LOGGER.info("Data da Consulta: " + dateFormat.format(new Date()));
		buscaVendasService.buscarVendas(2, "P", "170209", "1400");
	}
}
