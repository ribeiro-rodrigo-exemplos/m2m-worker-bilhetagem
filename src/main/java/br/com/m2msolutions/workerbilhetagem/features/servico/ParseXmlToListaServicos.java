package br.com.m2msolutions.workerbilhetagem.features.servico;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class ParseXmlToListaServicos {

	public static ListaServicos parse(String xmlString) throws JAXBException {
		ListaServicos listaServicos = new ListaServicos();

		JAXBContext jaxbContext = JAXBContext.newInstance(ListaServicos.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

		StringReader xml = new StringReader(xmlString);
		listaServicos = (ListaServicos) jaxbUnmarshaller.unmarshal(xml);

		return listaServicos;
	}
}
