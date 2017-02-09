package br.com.m2msolutions.workerbilhetagem.models;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "vendas")
@XmlAccessorType (XmlAccessType.FIELD)
public class ListaVendas {

	@XmlElement(name = "venda")
	private List<Venda> listaVendas = null;

	public List<Venda> getListaVendas() {
		return listaVendas;
	}

	public void setListaVendas(List<Venda> listaVendas) {
		this.listaVendas = listaVendas;
	}
}
