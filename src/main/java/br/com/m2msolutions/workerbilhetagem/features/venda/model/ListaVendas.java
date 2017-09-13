package br.com.m2msolutions.workerbilhetagem.features.venda.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "vendas")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListaVendas {

	@XmlElement(name = "venda")
	private List<Venda> listaVendas;

	@XmlTransient
	private boolean foraDoPeriodo = false;

	public ListaVendas(){
	    listaVendas = new ArrayList<>();
    }

	public List<Venda> getListaVendas() {
		return listaVendas;
	}

	public void setListaVendas(List<Venda> listaVendas) {
		this.listaVendas = listaVendas;
	}

	public boolean isForaDoPeriodo(){
		return foraDoPeriodo;
	}

	public void setForaDoPeriodo(boolean foraDoPeriodo){
		this.foraDoPeriodo = foraDoPeriodo;
	}
}
