package br.com.m2msolutions.workerbilhetagem.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.m2msolutions.workerbilhetagem.authentication.BasicAuthenticationInterceptor;
import br.com.m2msolutions.workerbilhetagem.commom.CodigoErroEnum;
import br.com.m2msolutions.workerbilhetagem.commom.Config;
import br.com.m2msolutions.workerbilhetagem.commom.Const;
import br.com.m2msolutions.workerbilhetagem.models.ListaVendas;
import br.com.m2msolutions.workerbilhetagem.models.Venda;
import br.com.m2msolutions.workerbilhetagem.parse.ParseListaVendasToJson;
import br.com.m2msolutions.workerbilhetagem.parse.ParseXmlToListaVendas;

@Component
public class BuscaVendasService {
	private Logger LOGGER = LoggerFactory.getLogger(BuscaVendasService.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	private Config config;

	@Scheduled(fixedRateString = "${schedule.timer}")
	public void buscarVendasService() {
		RestTemplate restTemplate = new RestTemplate();

		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		interceptors.add(new BasicAuthenticationInterceptor(config.getUsernameRJ(), config.getPasswordRJ()));
		restTemplate.setInterceptors(interceptors);

		LOGGER.info("Data da Consulta: " + dateFormat.format(new Date()));

		String listaVendasXml = restTemplate.getForObject(Const.RjWebServiceUrl + "buscaVendas/2/P/170209/1200",
				String.class);

		try {
			ListaVendas listaVendas = ParseXmlToListaVendas.parse(listaVendasXml);

			for (Venda venda : listaVendas.getListaVendas()) {
				if (venda.getCodRetorno() != null) {
					CodigoErroEnum erro = CodigoErroEnum.valueOf("Cod" + venda.getCodRetorno());
					LOGGER.error("Error: " + erro);
				}
			}

			LOGGER.info(ParseListaVendasToJson.parse(listaVendas));

		} catch (JAXBException e) {
			LOGGER.error(e.toString());
		}

	}
}
