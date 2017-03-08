package br.com.m2msolutions.workerbilhetagem.queue;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.BindingBuilder.DirectExchangeRoutingKeyConfigurer;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
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
	public Queue persistenceRouteQueue() {
		return new Queue(config.getQueueName());
	}

	@Bean
	public Queue reprocessRouteQueue() {
		return new Queue(config.getQueueReprocessName());
	}

	@Bean
	public DirectExchange persistenceRouteExchange() {
		DirectExchange exchange = new DirectExchange(config.getRabbitRoutingKey());
		return exchange;
	}

	@Bean
	public DirectExchangeRoutingKeyConfigurer persistenceRouteBinding() {
		return BindingBuilder.bind(persistenceRouteQueue()).to(persistenceRouteExchange());
	}

	@Bean
	public DirectExchangeRoutingKeyConfigurer reprocessRouteBinding() {
		return BindingBuilder.bind(reprocessRouteQueue()).to(persistenceRouteExchange());
	}

}
