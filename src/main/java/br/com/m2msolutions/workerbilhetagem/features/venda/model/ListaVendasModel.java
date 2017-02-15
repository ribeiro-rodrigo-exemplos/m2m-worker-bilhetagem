package br.com.m2msolutions.workerbilhetagem.features.venda.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "vendas")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListaVendasModel {

	@XmlElement(name = "venda")
	private List<VendaModel> listaVendas = null;

	public List<VendaModel> getListaVendas() {
		return listaVendas;
	}

	public void setListaVendas(List<VendaModel> listaVendas) {
		this.listaVendas = listaVendas;
	}
}
