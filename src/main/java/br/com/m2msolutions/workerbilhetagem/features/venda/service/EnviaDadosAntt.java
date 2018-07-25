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
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import br.com.m2msolutions.workerbilhetagem.authentication.AnttAuthenticationInterceptor;
import br.com.m2msolutions.workerbilhetagem.commom.AnttMessageSuccess;
import br.com.m2msolutions.workerbilhetagem.commom.errors.AnttError;
import br.com.m2msolutions.workerbilhetagem.commom.errors.AnttErrorList;
import br.com.m2msolutions.workerbilhetagem.config.Config;
import br.com.m2msolutions.workerbilhetagem.features.cliente.ClienteRjConsultores;
import br.com.m2msolutions.workerbilhetagem.features.cliente.ClienteRjConsultoresRepository;
import br.com.m2msolutions.workerbilhetagem.features.venda.model.ListaVendas;
import br.com.m2msolutions.workerbilhetagem.features.venda.model.Venda;
import br.com.m2msolutions.workerbilhetagem.features.venda.util.ParseListaVendasToAntt;
import br.com.m2msolutions.workerbilhetagem.features.venda.util.VendasUtil;

@Component
public class EnviaDadosAntt {
	private Logger LOGGER = LoggerFactory.getLogger(EnviaDadosAntt.class);

	@Autowired
	ParseListaVendasToAntt parseListaVendasToAntt;

	@Autowired
	EnviaDadosRabbit enviaDadosRabbitService;

	@Autowired
	ClienteRjConsultoresRepository clienteRjConsultoresRepository;

	@Autowired
	VendasUtil vendasUtil;

	@Autowired
	private Config config;

	public void enviar(ListaVendas listaVendas, ClienteRjConsultores clienteRj, String url) {

		if(listaVendas.isForaDoPeriodo()){
			clienteRj.setNow();
			listaVendas.getListaVendas().clear();		}
		else
			clienteRj.nextMinute();

		for (Venda venda : listaVendas.getListaVendas()) {
			String urlAntt = config.getAnttUrl();
			
			if(venda.getStatus() != 0) {
				
			
				urlAntt += config.getInserirCancelamento();				
				
				String json = parseListaVendasToAntt.parseCancelamento(venda, config.getCodCancelamento(), null);
				AnttMessageSuccess postAnttSuccess = postAntt(json,url, urlAntt);
							
				if(postAnttSuccess != null){
					String jsonRabbit = parseListaVendasToAntt.parseCancelamento(venda, config.getCodCancelamento(), postAnttSuccess.getIdTransacao());
	//				LOGGER.info("jsonRabbit: {} ", jsonRabbit);
					enviaDadosRabbitService.enviar(jsonRabbit, clienteRj, postAnttSuccess, venda.getStatus());
				}
			}else {
				
				urlAntt += config.getInserirVenda();
			
				// config.setAnttUrl(urlAntt);
				
				String json = parseListaVendasToAntt.parse(venda, clienteRj,null);
				AnttMessageSuccess postAnttSuccess = postAntt(json,url,urlAntt);
							
				if(postAnttSuccess != null){
					String jsonRabbit = parseListaVendasToAntt.parse(venda, clienteRj,postAnttSuccess.getIdTransacao());
	//				LOGGER.info("jsonRabbit: {} ", jsonRabbit);
					enviaDadosRabbitService.enviar(jsonRabbit, clienteRj, postAnttSuccess, venda.getStatus());
				}
		  }
		}

		clienteRjConsultoresRepository.save(clienteRj);

		LOGGER.info("Cliente: {} - Ultima Venda: {}", clienteRj.getCliente().getNmNome(), clienteRj.getDataEnvio());
	}

	private AnttMessageSuccess postAntt(String json, String url, String urlAntt) {
		Gson gson = new Gson();
		AnttMessageSuccess anttSuccess = null;

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity<>(json, headers);

		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		interceptors.add(new AnttAuthenticationInterceptor(config.getAnttToken()));

		restTemplate.setInterceptors(interceptors);

		try {
			HttpEntity<String> response = restTemplate.exchange(urlAntt, HttpMethod.POST, entity,
					String.class);

			anttSuccess = gson.fromJson(response.getBody(), AnttMessageSuccess.class);
			LOGGER.info("Mensagem: {} - idTransacao: {}", anttSuccess.getMensagem(), anttSuccess.getIdTransacao());
			LOGGER.info("Serviço Antt: {} ", urlAntt);
			anttSuccess.setSuccess(true);

		} catch (HttpClientErrorException ex) {
			AnttError error = gson.fromJson(ex.getResponseBodyAsString(), AnttError.class);
			LOGGER.error("URL - {}",url);
			LOGGER.error("Erro ao Enviar Dados ANTT - Total Erros: {} - Codigo: {} - Mensagem: {}",
					error.getErros().size(), error.getCodigo(), error.getMensagem());

			LOGGER.error(json);

			for (AnttErrorList errorList : error.getErros()) {
				LOGGER.info("Erro: {} - Descricao: {}", errorList.getCodigo(), errorList.getDescricao());
			}
		} catch (RestClientException e) {
			LOGGER.error("Erro - {}", e.toString());
		}

		return anttSuccess;
	}
}
