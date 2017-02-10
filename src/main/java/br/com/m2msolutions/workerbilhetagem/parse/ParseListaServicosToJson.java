package br.com.m2msolutions.workerbilhetagem.parse;

import com.google.gson.Gson;

import br.com.m2msolutions.workerbilhetagem.models.ListaServicos;

public class ParseListaServicosToJson {
	public static String parse(ListaServicos listaServicos) {
		Gson gson = new Gson();
		return gson.toJson(listaServicos);
	}
}
