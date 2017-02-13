package br.com.m2msolutions.workerbilhetagem.features.venda;

import com.google.gson.Gson;

public class ParseListaVendasToJson {
	public static String parse(ListaVendasModel listaVendas) {
		Gson gson = new Gson();
		return gson.toJson(listaVendas);
	}
}
