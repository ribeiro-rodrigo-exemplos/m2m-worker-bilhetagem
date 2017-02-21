package br.com.m2msolutions.workerbilhetagem.features.venda.service;

import java.util.ArrayList;
import java.util.List;

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

import com.google.gson.Gson;

import br.com.m2msolutions.workerbilhetagem.authentication.AnttAuthenticationInterceptor;
import br.com.m2msolutions.workerbilhetagem.commom.AnttMessageSuccess;
import br.com.m2msolutions.workerbilhetagem.commom.Config;
import br.com.m2msolutions.workerbilhetagem.commom.errors.AnttError;
import br.com.m2msolutions.workerbilhetagem.commom.errors.AnttErrorList;
import br.com.m2msolutions.workerbilhetagem.features.cliente.ClienteRjConsultores;
import br.com.m2msolutions.workerbilhetagem.features.cliente.ClienteRjConsultoresRepository;
import br.com.m2msolutions.workerbilhetagem.features.venda.model.ListaVendasModel;
import br.com.m2msolutions.workerbilhetagem.features.venda.model.VendaModel;
import br.com.m2msolutions.workerbilhetagem.features.venda.util.ParseListaVendasToAntt;
import br.com.m2msolutions.workerbilhetagem.features.venda.util.VendasUtil;

@Component
public class EnviaDadosAnttService {
	private Logger LOGGER = LoggerFactory.getLogger(EnviaDadosAnttService.class);

	@Autowired
	ParseListaVendasToAntt parseListaVendasToAntt;

	@Autowired
	EnviaDadosRabbitService enviaDadosRabbitService;

	@Autowired
	ClienteRjConsultoresRepository clienteRjConsultoresRepository;

	@Autowired
	VendasUtil vendasUtil;

	@Autowired
	private Config config;

	public void enviar(ListaVendasModel listaVendas, ClienteRjConsultores clienteRj) {
		LOGGER.info("Enviar Dados ANTT");

		List<String> listaLogVendaJson = new ArrayList<String>();

		for (VendaModel venda : listaVendas.getListaVendas()) {
			listaLogVendaJson.add(parseListaVendasToAntt.parse(venda));
		}

		for (String json : listaLogVendaJson) {
			boolean sendToRabbit = postAntt(json);
			if (sendToRabbit) {
				enviaDadosRabbitService.enviar(json, clienteRj);
			}
		}

		String dataUltimaVenda = vendasUtil.getDataHoraUltimaVenda(listaVendas);
		clienteRj.setDataEnvio(dataUltimaVenda);
		clienteRjConsultoresRepository.save(clienteRj);

		LOGGER.info("Cliente: {} - Ultima Venda: {}", clienteRj.getCliente().getNome(), dataUltimaVenda);
	}

	private boolean postAntt(String json) {
		Gson gson = new Gson();
		boolean postSuccess = false;

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity<>(json, headers);

		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		interceptors.add(new AnttAuthenticationInterceptor(config.getAnttToken()));
		restTemplate.setInterceptors(interceptors);

		try {
			HttpEntity<String> response = restTemplate.exchange(config.getAnttUrl(), HttpMethod.POST, entity,
					String.class);

			AnttMessageSuccess anttSuccess = gson.fromJson(response.getBody(), AnttMessageSuccess.class);
			LOGGER.info("Mensagem: {} - idTransacao: {}", anttSuccess.getMensagem(), anttSuccess.getIdTransacao());

			postSuccess = true;

		} catch (HttpClientErrorException ex) {
			AnttError error = gson.fromJson(ex.getResponseBodyAsString(), AnttError.class);

			LOGGER.error("Erro ao Enviar Dados ANTT - Total Erros: {} - Codigo: {} - Mensagem: {}",
					error.getErros().size(), error.getCodigo(), error.getMensagem());

			for (AnttErrorList errorList : error.getErros()) {
				LOGGER.info("Erro: {} - Descricao: {}", errorList.getCodigo(), errorList.getDescricao());
			}

			LOGGER.error(json);
		}
		return postSuccess;
	}
}
