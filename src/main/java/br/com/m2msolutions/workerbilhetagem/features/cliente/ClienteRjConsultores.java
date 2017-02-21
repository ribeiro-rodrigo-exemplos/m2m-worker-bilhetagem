package br.com.m2msolutions.workerbilhetagem.features.cliente;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "cliente_rjconsultores")
public class ClienteRjConsultores {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Cliente cliente;

	@NotNull
	@Column(name = "cod_conexao")
	private String codConexao;

	@NotNull
	@Column(name = "cod_cliente")
	private String codCliente;

	@NotNull
	@Column(name = "dt_sincronismo_venda_bilhetes")
	private String dataEnvio;

	public ClienteRjConsultores() {
	}

	public ClienteRjConsultores(Cliente cliente, String codConexao, String codCliente, String dataEnvio) {
		this.cliente = cliente;
		this.codConexao = codConexao;
		this.codCliente = codCliente;
		this.dataEnvio = dataEnvio;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getCodConexao() {
		return codConexao;
	}

	public void setCodConexao(String codConexao) {
		this.codConexao = codConexao;
	}

	public String getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}

	public String getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(String dataEnvio) {
		this.dataEnvio = dataEnvio;
	}
}