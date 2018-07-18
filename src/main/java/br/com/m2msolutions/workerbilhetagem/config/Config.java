package br.com.m2msolutions.workerbilhetagem.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Config {

	@Value("${rjconsultores.webservice.url}")
	private String RjWebServiceUrl;

	@Value("${authentication.rj.username}")
	private String usernameRJ;

	@Value("${authentication.rj.password}")
	private String passwordRJ;

	@Value("${authentication.antt.url}")
	private String anttUrl;
	
	@Value("${authentication.antt.venda}")
	private String inserirVenda;

	@Value("${authentication.antt.cancelamento}")
	private String inserirCancelamento;

	@Value("${authentication.antt.token}")
	private String anttToken;

	@Value("${spring.rabbitmq.exchange}")
	private String exchange;

	@Value("${spring.rabbitmq.queue.reprocess.name}")
	private String queueReprocessName;

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

	@Value("${collection.name}")
	private String collectionName;

	@Value("${savedatatofile}")
	private boolean saveDataToFile;

	@Value("${lazypersistence.database}")
	private String lazyPersistenceDatabase;

	@Value("${url-zona}")
    private String urlZona;

	@Value("${cod-cancelamento}")
    private String codCancelamento;

	public String getLazyPersistenceDatabase() {
		return lazyPersistenceDatabase;
	}

	public void setLazyPersistenceDatabase(String lazyPersistenceDatabase) {
		this.lazyPersistenceDatabase = lazyPersistenceDatabase;
	}

	public String getUrlZona() {
        return urlZona;
    }

    public void setUrlZona(String urlZona) {
        this.urlZona = urlZona;
    }

    public String getRjWebServiceUrl() {
		return RjWebServiceUrl;
	}

	public void setRjWebServiceUrl(String rjWebServiceUrl) {
		RjWebServiceUrl = rjWebServiceUrl;
	}

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

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
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

	public String getAnttToken() {
		return anttToken;
	}

	public void setAnttToken(String anttToken) {
		this.anttToken = anttToken;
	}

	public String getAnttUrl() {
		return anttUrl;
	}

	public void setAnttUrl(String anttUrl) {
		this.anttUrl = anttUrl;
	}

	public String getQueueReprocessName() {
		return queueReprocessName;
	}

	public void setQueueReprocessName(String queueReprocessName) {
		this.queueReprocessName = queueReprocessName;
	}

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	public boolean isSaveDataToFile() {
		return saveDataToFile;
	}

	public void setSaveDataToFile(boolean saveDataToFile) {
		this.saveDataToFile = saveDataToFile;
	}

	public String getInserirVenda() {
		return inserirVenda;
	}

	public void setInserirVenda(String inserirVenda) {
		this.inserirVenda = inserirVenda;
	}

	public String getInserirCancelamento() {
		return inserirCancelamento;
	}

	public void setInserirCancelamento(String inserirCancelamento) {
		this.inserirCancelamento = inserirCancelamento;
	}

	public String getCodCancelamento() {
		return codCancelamento;
	}

	public void setCodCancelamento(String codCancelamento) {
		this.codCancelamento = codCancelamento;
	}
}
