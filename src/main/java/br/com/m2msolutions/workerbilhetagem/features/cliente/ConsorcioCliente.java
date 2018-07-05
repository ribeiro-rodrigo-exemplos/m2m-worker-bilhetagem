package br.com.m2msolutions.workerbilhetagem.features.cliente;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

 
@Entity
@Table(name = "consorcio_cliente")
public class ConsorcioCliente implements Serializable{

	private static final long serialVersionUID = 4710886674537605632L;

	@Id
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne
    @JoinColumn(name="id_cliente")
	private Cliente cliente;
	
	@Column(name = "nm_consorcio")
	private String nomeConsorcio;

	@Column(name = "nm_empresa")
	private String nomeEmpresa;
	
	@Column(name = "cd_cnpj_empresa")
	private String cnpjEmpresa;
	
	@Column(name = "cd_cnpj_consorcio")
	private String cnpjConsorcio;

	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public String getNomeConsorcio() {
		return nomeConsorcio;
	}

	public void setNomeConsorcio(String nomeConsorcio) {
		this.nomeConsorcio = nomeConsorcio;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getCnpjEmpresa() {
		return cnpjEmpresa;
	}

	public void setCnpjEmpresa(String cnpjEmpresa) {
		this.cnpjEmpresa = cnpjEmpresa;
	}

	public String getCnpjConsorcio() {
		return cnpjConsorcio;
	}

	public void setCnpjConsorcio(String cnpjConsorcio) {
		this.cnpjConsorcio = cnpjConsorcio;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	
}
