package br.com.m2msolutions.workerbilhetagem.queue;

import org.springframework.amqp.core.*;
import org.springframework.amqp.core.BindingBuilder.DirectExchangeRoutingKeyConfigurer;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.m2msolutions.workerbilhetagem.config.Config;

@Configuration
public class RabbitConfig {

	@Autowired
	private Config config;

	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setHost(config.getRabbitIp());
		connectionFactory.setPort(config.getRabbitPort());
		connectionFactory.setUsername(config.getRabbitUser());
		connectionFactory.setPassword(config.getRabbitPassword());
		return connectionFactory;
	}

	@Bean
	public AmqpAdmin amqpAdmin() {
		RabbitAdmin admin = new RabbitAdmin(connectionFactory());
		return admin;
	}

	@Bean
	public RabbitTemplate rabbitTemplate() {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
		return rabbitTemplate;
	}

	@Bean
	public Queue reprocessRouteQueue() {
		return new Queue(config.getQueueReprocessName());
	}

	@Bean
	public TopicExchange persistenceRouteExchange() {
		TopicExchange exchange = new TopicExchange(config.getExchange());
		return exchange;
	}

}
