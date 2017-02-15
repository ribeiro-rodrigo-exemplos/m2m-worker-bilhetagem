package br.com.m2msolutions.workerbilhetagem.features.venda.util;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import br.com.m2msolutions.workerbilhetagem.features.venda.model.ListaVendasModel;

public class ParseXmlToListaVendas {

	public static ListaVendasModel parse(String xmlString) throws JAXBException {
		ListaVendasModel listaVendas = new ListaVendasModel();

		JAXBContext jaxbContext = JAXBContext.newInstance(ListaVendasModel.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

		StringReader xml = new StringReader(xmlString);
		listaVendas = (ListaVendasModel) jaxbUnmarshaller.unmarshal(xml);

		return listaVendas;
	}
}
