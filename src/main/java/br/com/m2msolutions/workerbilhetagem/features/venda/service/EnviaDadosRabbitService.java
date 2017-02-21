package br.com.m2msolutions.workerbilhetagem.features.venda.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.m2msolutions.workerbilhetagem.commom.Config;
import br.com.m2msolutions.workerbilhetagem.features.cliente.ClienteRjConsultores;

@Component
public class EnviaDadosRabbitService {
	private Logger LOGGER = LoggerFactory.getLogger(EnviaDadosRabbitService.class);

	@Autowired
	private Config config;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void enviar(String json, ClienteRjConsultores clienteRj) {
		try {
			rabbitTemplate.convertAndSend(config.getQueueName(), json);
			LOGGER.info("Informacao enviada a Fila - Cliente: {}", clienteRj.getCliente().getNome());
		} catch (AmqpException ex) {
			LOGGER.error("Erro ao Enviar Informacao a Fila: {}", config.getQueueName());
			LOGGER.error("Erro: {}", ex.toString());
		}
	}
}
