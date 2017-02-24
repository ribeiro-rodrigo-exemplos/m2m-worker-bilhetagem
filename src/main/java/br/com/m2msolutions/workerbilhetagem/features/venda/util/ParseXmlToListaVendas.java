package br.com.m2msolutions.workerbilhetagem.features.venda.util;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import br.com.m2msolutions.workerbilhetagem.features.venda.model.ListaVendas;

public class ParseXmlToListaVendas {
	public static ListaVendas parse(String xmlString) throws JAXBException {
		Unmarshaller jaxbUnmarshaller = JAXBContext.newInstance(ListaVendas.class).createUnmarshaller();
		ListaVendas listaVendas = (ListaVendas) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));
		return listaVendas;
	}
}
