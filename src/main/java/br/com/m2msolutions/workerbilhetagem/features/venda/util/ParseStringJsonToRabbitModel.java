package br.com.m2msolutions.workerbilhetagem.features.venda.util;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.m2msolutions.workerbilhetagem.features.venda.model.Venda;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.m2msolutions.workerbilhetagem.config.Config;
import br.com.m2msolutions.workerbilhetagem.features.cliente.ClienteRjConsultores;

@Component
public class ParseStringJsonToRabbitModel {
	@Autowired
	private Config config;
	private ObjectMapper mapper = new ObjectMapper();

	public String parse(Venda data, ClienteRjConsultores clienteRj) {

		try{

		StringWriter writer = new StringWriter();
		mapper.writeValue(writer,data);
		JsonElement element = new JsonParser().parse(writer.toString());
		JsonObject jobject = element.getAsJsonObject();
		jobject.addProperty("dt_atualizacao", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		jobject.addProperty("clienteId", clienteRj.getCliente().getIdCliente());
		jobject.addProperty("dataHoraEvento", clienteRj.getDataEnvio());

		JsonObject innerObject = new JsonObject();
		innerObject.addProperty("collection", config.getCollectionName());
		innerObject.addProperty("action", "insert");
		innerObject.add("data", element);

		return innerObject.toString();

		}
		catch(IOException e){
			return "";
		}
	}
}
