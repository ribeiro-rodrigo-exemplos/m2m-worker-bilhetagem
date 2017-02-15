package br.com.m2msolutions.workerbilhetagem.commom;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Config {
	@Value("${authentication.rj.username}")
	private String usernameRJ;

	@Value("${authentication.rj.password}")
	private String passwordRJ;

	@Value("${spring.rabbitmq.queue.name}")
	private String queueName;

	@Value("${spring.rabbitmq.addresses}")
	private String rabbitIp;

	@Value("${spring.rabbitmq.port}")
	private Integer rabbitPort;

	@Value("${spring.rabbitmq.username}")
	private String rabbitUser;

	@Value("${spring.rabbitmq.password}")
	private String rabbitPassword;

	@Value("${spring.rabbitmq.routingkey}")
	private String rabbitRoutingKey;

	public String getUsernameRJ() {
		return usernameRJ;
	}

	public void setUsernameRJ(String usernameRJ) {
		this.usernameRJ = usernameRJ;
	}

	public String getPasswordRJ() {
		return passwordRJ;
	}

	public void setPasswordRJ(String passwordRJ) {
		this.passwordRJ = passwordRJ;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public String getRabbitIp() {
		return rabbitIp;
	}

	public void setRabbitIp(String rabbitIp) {
		this.rabbitIp = rabbitIp;
	}

	public Integer getRabbitPort() {
		return rabbitPort;
	}

	public void setRabbitPort(Integer rabbitPort) {
		this.rabbitPort = rabbitPort;
	}

	public String getRabbitUser() {
		return rabbitUser;
	}

	public void setRabbitUser(String rabbitUser) {
		this.rabbitUser = rabbitUser;
	}

	public String getRabbitPassword() {
		return rabbitPassword;
	}

	public void setRabbitPassword(String rabbitPassword) {
		this.rabbitPassword = rabbitPassword;
	}

	public String getRabbitRoutingKey() {
		return rabbitRoutingKey;
	}

	public void setRabbitRoutingKey(String rabbitRoutingKey) {
		this.rabbitRoutingKey = rabbitRoutingKey;
	}
}
