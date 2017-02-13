package br.com.m2msolutions.workerbilhetagem.features.venda;

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
import org.springframework.web.client.RestTemplate;

import br.com.m2msolutions.workerbilhetagem.authentication.BasicAuthenticationInterceptor;
import br.com.m2msolutions.workerbilhetagem.commom.CodigoErroEnum;
import br.com.m2msolutions.workerbilhetagem.commom.Config;
import br.com.m2msolutions.workerbilhetagem.commom.Const;

@Component
public class BuscaVendasService {
	private Logger LOGGER = LoggerFactory.getLogger(BuscaVendasService.class);
	private String methodName = "buscaVendas";

	@Autowired
	private Config config;

	public boolean buscarVendas(Integer codConexao, String codEmpresa, String data, String hora) {
		boolean buscaCompleta = false;

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<?> entity = new HttpEntity<>(headers);

		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		interceptors.add(new BasicAuthenticationInterceptor(config.getUsernameRJ(), config.getPasswordRJ()));
		restTemplate.setInterceptors(interceptors);

		StringBuilder url = new StringBuilder();

		url.append(Const.RjWebServiceUrl);
		url.append(methodName + "/");
		url.append(codConexao + "/");
		url.append(codEmpresa + "/");
		url.append(data + "/");
		url.append(hora);

		try {
			HttpEntity<String> response = restTemplate.exchange(url.toString(), HttpMethod.GET, entity, String.class);

			if (response.getHeaders().getContentType().equals(MediaType.TEXT_XML)) {
				String listaVendasXml = response.getBody();
				try {
					ListaVendasModel listaVendas = ParseXmlToListaVendas.parse(listaVendasXml);

					for (VendaModel venda : listaVendas.getListaVendas()) {
						if (venda.getCodRetorno() != null) {
							CodigoErroEnum erro = CodigoErroEnum.valueOf("Cod" + venda.getCodRetorno());
							LOGGER.error("Erro - " + erro);
						}
					}

					buscaCompleta = true;
					LOGGER.info(ParseListaVendasToJson.parse(listaVendas));
				} catch (JAXBException e) {
					LOGGER.error("Erro - " + e.toString());
				}
			} else {
				LOGGER.error("Content Type Inválido para processamento: " + response.getHeaders().getContentType());
			}
		} catch (HttpClientErrorException ex) {
			CodigoErroEnum erro = CodigoErroEnum.valueOf("Cod" + ex.getStatusCode());
			LOGGER.error("Erro - " + erro);
		}
		return buscaCompleta;
	}
}
