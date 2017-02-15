package br.com.m2msolutions.workerbilhetagem.features.venda.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.m2msolutions.workerbilhetagem.features.venda.model.ListaVendasModel;

public class ParseListaVendasToJson {
	public static String parse(ListaVendasModel listaVendas) {
		Gson gson = new Gson();
		String listaVendasJson = gson.toJson(listaVendas);

		JsonParser jsonParser = new JsonParser();
		JsonElement element = jsonParser.parse(listaVendasJson);

		JsonObject innerObject = new JsonObject();
		innerObject.addProperty("collection", "nome_da_colecao");
		innerObject.addProperty("action", "insert");
		innerObject.add("data", element);

		return innerObject.toString();
	}
}
