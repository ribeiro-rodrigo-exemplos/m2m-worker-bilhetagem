package br.com.m2msolutions.workerbilhetagem.features.servico;

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
public class BuscaServicosService {
	private Logger LOGGER = LoggerFactory.getLogger(BuscaServicosService.class);
	private String methodName = "buscaServico";

	@Autowired
	private Config config;

	public boolean buscarServicos(Integer codConexao, String codEmpresa, String data) {
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
		url.append(data + "/");
		url.append(codEmpresa);

		try {
			HttpEntity<String> response = restTemplate.exchange(url.toString(), HttpMethod.GET, entity, String.class);

			if (response.getHeaders().getContentType().equals(MediaType.TEXT_XML)) {
				String listaServicosXml = response.getBody();
				try {
					ListaServicos listaServicos = ParseXmlToListaServicos.parse(listaServicosXml);
					buscaCompleta = true;
					LOGGER.info(ParseListaServicosToJson.parse(listaServicos));
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
