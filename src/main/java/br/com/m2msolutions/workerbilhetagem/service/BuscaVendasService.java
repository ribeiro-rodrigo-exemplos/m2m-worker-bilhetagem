package br.com.m2msolutions.workerbilhetagem.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.m2msolutions.workerbilhetagem.authentication.BasicAuthenticationInterceptor;

@Component
public class BuscaVendasService {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(fixedRateString = "${schedule.timer}")
	public void buscarVendasService() {
		RestTemplate restTemplate = new RestTemplate();

		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		interceptors.add(new BasicAuthenticationInterceptor("m2m", "rjm2mrj"));
		restTemplate.setInterceptors(interceptors);

		String response = restTemplate.getForObject("http://34.198.151.111:9991/WSMonitriip", String.class);

		System.out.println("Data da Consulta: " + dateFormat.format(new Date()));
		System.out.println(response);

		// Venda venda =
		// restTemplate.getForObject("http://echo.jsontest.com/idLog/10/identificadorBilhete/12",
		// Venda.class);

		// System.out.println("IdLog: " + venda.getIdLog());
		// System.out.println("IdentificadorBilhete: " +
		// venda.getIdentificadorBilhete());
		// System.out.println("CNPJ: " + venda.getCnpj());
	}
}
