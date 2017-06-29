package br.com.m2msolutions.workerbilhetagem.commom;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "resultadoOperacao")
@XmlAccessorType(XmlAccessType.FIELD)
public class AnttMessageSuccess {

	private String codigo;
	private String mensagem;
	private String idTransacao;
	private boolean success = false;

	public boolean isSuccess(){
		return success;
	}

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean getSuccess(){
		return this.success;
	}

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

	public String getIdTransacao() {
		return idTransacao;
	}

	public void setIdTransacao(String idTransacao) {
		this.idTransacao = idTransacao;
	}

}
