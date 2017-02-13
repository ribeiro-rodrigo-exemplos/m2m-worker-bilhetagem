package br.com.m2msolutions.workerbilhetagem.features.servico;

import com.google.gson.Gson;

public class ParseListaServicosToJson {
	public static String parse(ListaServicos listaServicos) {
		Gson gson = new Gson();
		return gson.toJson(listaServicos);
	}
}
