package br.com.m2msolutions.workerbilhetagem.features.venda.service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.m2msolutions.workerbilhetagem.authentication.RjAuthenticationInterceptor;
import br.com.m2msolutions.workerbilhetagem.commom.errors.CodigoErroEnum;
import br.com.m2msolutions.workerbilhetagem.config.Config;
import br.com.m2msolutions.workerbilhetagem.features.cliente.ClienteRjConsultores;
import br.com.m2msolutions.workerbilhetagem.features.venda.model.ListaVendas;
import br.com.m2msolutions.workerbilhetagem.features.venda.model.Venda;
import br.com.m2msolutions.workerbilhetagem.features.venda.util.ParseXmlToListaVendas;

@Component
public class BuscaVendas {
	private Logger LOGGER = LoggerFactory.getLogger(BuscaVendas.class);

	@Autowired
	private Config config;

	public ListaVendas buscarVendas(String url, ClienteRjConsultores clienteRj) {
		CodigoErroEnum erro = null;
		ListaVendas listaVendas = null;

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<?> entity = new HttpEntity<>(headers);

		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		interceptors.add(new RjAuthenticationInterceptor(config.getUsernameRJ(), config.getPasswordRJ()));
		restTemplate.setInterceptors(interceptors);

		try {
			HttpEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

			if (response.getHeaders().getContentType().equals(MediaType.TEXT_XML)) {
				String listaVendasXml = response.getBody();
				try {
					ListaVendas listaVendasModel = ParseXmlToListaVendas.parse(listaVendasXml);

					for (Venda venda : listaVendasModel.getListaVendas()) {
						if (venda.getCodRetorno() != null) {
							erro = CodigoErroEnum.valueOf("Cod" + venda.getCodRetorno());
							LOGGER.error("Erro - {} - Cliente: {}", erro, clienteRj.getCliente().getNome());
						}
					}
					if (erro == null) {
						listaVendas = listaVendasModel;
					}
				} catch (JAXBException e) {
					LOGGER.error("Erro - {}", e.toString());
				}
			} else {
				LOGGER.error("Content Type Invalido para processamento: " + response.getHeaders().getContentType());
			}
		} catch (HttpClientErrorException ex) {
			erro = CodigoErroEnum.valueOf("Cod" + ex.getStatusCode());
			LOGGER.error("Erro - {}", erro);
		} catch (HttpServerErrorException ex) {
			erro = CodigoErroEnum.valueOf("Cod" + ex.getStatusCode());
			LOGGER.error("Erro - {} - Cliente: {} ", erro, clienteRj.getCliente().getNome());
		}
		return listaVendas;
	}
}
