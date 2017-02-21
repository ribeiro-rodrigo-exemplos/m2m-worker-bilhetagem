package br.com.m2msolutions.workerbilhetagem.commom.errors;

import java.util.List;

public class AnttError {
	private String codigo;
	private String mensagem;
	private List<AnttErrorList> erros = null;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public List<AnttErrorList> getErros() {
		return erros;
	}

	public void setErrors(List<AnttErrorList> erros) {
		this.erros = erros;
	}

}
