package br.com.m2msolutions.workerbilhetagem.features.venda.service;

import br.com.m2msolutions.workerbilhetagem.commom.AnttMessageSuccess;
import br.com.m2msolutions.workerbilhetagem.features.venda.model.Venda;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.m2msolutions.workerbilhetagem.config.Config;
import br.com.m2msolutions.workerbilhetagem.features.cliente.ClienteRjConsultores;
import br.com.m2msolutions.workerbilhetagem.features.venda.util.ParseStringJsonToRabbitModel;
import br.com.m2msolutions.workerbilhetagem.features.venda.util.VendasUtil;

import java.util.Map;

@Component
public class EnviaDadosRabbit {
	private Logger LOGGER = LoggerFactory.getLogger(EnviaDadosRabbit.class);

	@Autowired
	private Config config;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private VendasUtil vendasUtil;

	@Autowired
	ParseStringJsonToRabbitModel parseStringJsonToRabbitModel;

	public void enviar(String json, ClienteRjConsultores clienteRj, AnttMessageSuccess postAnttSuccess, int status) {
		String data = parseStringJsonToRabbitModel.parse(json, clienteRj);

		if (config.isSaveDataToFile())
			vendasUtil.saveIntoFile(data, "example.json");

		try {
			if (postAnttSuccess != null && postAnttSuccess.isSuccess()) {
				rabbitTemplate.convertAndSend(config.getExchange(),config.getRabbitRoutingKey(), data,m-> {
					Map<String,Object> headers = m.getMessageProperties().getHeaders();
					m.getMessageProperties().setContentType("application/json");
					headers.put("action","insert");
					if(status != 0) {
						headers.put("collection","logsMonitrip");
					}else {
						headers.put("collection","Bilhetes");
					}
					headers.put("database",config.getLazyPersistenceDatabase());
					return m;
				});

				LOGGER.info("Informacao enviada ao exchange: {}  - Cliente: {}", config.getExchange(),
						clienteRj.getCliente().getNmNome());
			} else {
				rabbitTemplate.convertAndSend(config.getQueueReprocessName(), data);
				LOGGER.error("Informacao enviada a Fila: {} - Cliente: {}", config.getQueueReprocessName(),
						clienteRj.getCliente().getNmNome());
			}
		} catch (AmqpException ex) {
			LOGGER.error("Erro ao Enviar Informacao ao exchange: {}", config.getExchange());
			LOGGER.error("Erro: {}", ex.toString());
		}
	}
}
