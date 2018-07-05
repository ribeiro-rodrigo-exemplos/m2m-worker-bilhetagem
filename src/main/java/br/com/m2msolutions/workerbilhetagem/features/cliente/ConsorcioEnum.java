package br.com.m2msolutions.workerbilhetagem.features.cliente;

import java.util.ArrayList;
import java.util.List;

public enum ConsorcioEnum {

	
	
		Sampaio(33542531000165l,	"Consórcio Guanabara",	23542573000142l),
		Util(33337007000152l,	"Consórcio Guanabara",	23542573000142l),
		Brisa(5438013000160l,	"Consórcio Federal",	23562535000151l),
		Rapido(25634569000130l,	"Consórcio Federal",	23562535000151l),
	    Consorcio(25634551000138l, "Consórcio Real Expresso",	25634551000138l);

	 public Long cnpjEmpresa;
	 public String nomeConsorcio;
	 public Long cnpjConsorcio;

	private ConsorcioEnum(Long cnpjEmpresa, String nomeConsorcio, Long cnpjConsorcio) {
		 this.cnpjEmpresa = cnpjEmpresa;
		 this.nomeConsorcio = nomeConsorcio;
		 this.cnpjConsorcio =  cnpjConsorcio;
	}
	   
	
	public List<ConsorcioCliente> listaConsorcioCliente() {
		List<ConsorcioCliente> listaConsorcioCliente =  new ArrayList<ConsorcioCliente>();
		for (ConsorcioEnum ConsorcioEnum : ConsorcioEnum.values()) {
			ConsorcioCliente  c  = new ConsorcioCliente();
			c.setCnpjEmpresa(ConsorcioEnum.cnpjEmpresa.toString());
			c.setCnpjConsorcio(ConsorcioEnum.cnpjConsorcio.toString());
			c.setNomeConsorcio(ConsorcioEnum.nomeConsorcio);
			listaConsorcioCliente.add(c);
		}
        return listaConsorcioCliente;
    }
}
