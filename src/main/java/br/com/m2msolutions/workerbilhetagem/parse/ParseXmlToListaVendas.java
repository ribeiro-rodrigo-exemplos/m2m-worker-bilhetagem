package br.com.m2msolutions.workerbilhetagem.parse;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import br.com.m2msolutions.workerbilhetagem.models.ListaVendas;

public class ParseXmlToListaVendas {

	public static ListaVendas parse(String xmlString) throws JAXBException {
		ListaVendas listaVendas = new ListaVendas();

		JAXBContext jaxbContext = JAXBContext.newInstance(ListaVendas.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

		StringReader xml = new StringReader(xmlString);
		listaVendas = (ListaVendas) jaxbUnmarshaller.unmarshal(xml);

		return listaVendas;
	}
}
