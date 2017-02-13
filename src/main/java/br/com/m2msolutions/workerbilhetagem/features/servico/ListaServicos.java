package br.com.m2msolutions.workerbilhetagem.features.servico;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "servicoes")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListaServicos {

	@XmlElement(name = "servico")
	private List<Servico> listaServicos = null;

	public List<Servico> getListaServicos() {
		return listaServicos;
	}

	public void setListaServicos(List<Servico> listaServicos) {
		this.listaServicos = listaServicos;
	}
}
