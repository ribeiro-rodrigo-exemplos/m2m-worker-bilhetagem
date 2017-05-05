package br.com.m2msolutions.workerbilhetagem.features.venda.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InformacoesPassageiro {

	private String nomePassageiro;
	private String documentoIdentificacaoPassageiro;
	private String cpfPassageiro;
	private String celularPassageiro;

	public String getNomePassageiro() {
		return nomePassageiro;
	}

	public void setNomePassageiro(String nomePassageiro) {
		this.nomePassageiro = nomePassageiro;
	}

	public String getDocumentoIdentificacaoPassageiro() {
		return documentoIdentificacaoPassageiro;
	}

	public void setDocumentoIdentificacaoPassageiro(String documentoIdentificacaoPassageiro) {
		this.documentoIdentificacaoPassageiro = documentoIdentificacaoPassageiro;
	}

	public String getCpfPassageiro() {
		return cpfPassageiro;
	}

	public void setCpfPassageiro(String cpfPassageiro) {
		this.cpfPassageiro = cpfPassageiro;
	}

	public String getCelularPassageiro() {
		return celularPassageiro;
	}

	public void setCelularPassageiro(String celularPassageiro) {
		this.celularPassageiro = celularPassageiro;
	}
}