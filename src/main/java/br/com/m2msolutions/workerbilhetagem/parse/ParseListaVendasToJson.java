package br.com.m2msolutions.workerbilhetagem.parse;

import com.google.gson.Gson;

import br.com.m2msolutions.workerbilhetagem.models.ListaVendas;

public class ParseListaVendasToJson {
	public static String parse(ListaVendas listaVendas) {
		Gson gson = new Gson();
		return gson.toJson(listaVendas);
	}
}
