package br.com.m2msolutions.workerbilhetagem.features.venda.service;

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

	public void enviar(String json, ClienteRjConsultores clienteRj, boolean postAnttSuccess) {
		String data = parseStringJsonToRabbitModel.parse(json, clienteRj);

		if (config.isSaveDataToFile())
			vendasUtil.saveIntoFile(data, "example.json");

		try {
			if (postAnttSuccess) {
				rabbitTemplate.convertAndSend(config.getQueueName(), data);

				LOGGER.info("Informacao enviada a Fila: {}  - Cliente: {}", config.getQueueName(),
						clienteRj.getCliente().getNmNome());
			} else {
				rabbitTemplate.convertAndSend(config.getQueueReprocessName(), data);
				LOGGER.error("Informacao enviada a Fila: {} - Cliente: {}", config.getQueueReprocessName(),
						clienteRj.getCliente().getNmNome());
			}
		} catch (AmqpException ex) {
			LOGGER.error("Erro ao Enviar Informacao a Fila: {}", config.getQueueName());
			LOGGER.error("Erro: {}", ex.toString());
		}
	}
}
