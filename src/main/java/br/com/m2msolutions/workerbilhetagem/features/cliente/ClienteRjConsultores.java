package br.com.m2msolutions.workerbilhetagem.features.cliente;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.*;

import com.sun.istack.NotNull;

@Entity
@Table(name = "cliente_rjconsultores")
public class ClienteRjConsultores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne
    @JoinColumn(name="id_cliente")
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

	@NotNull
	@Column(name = "dt_sincronismo_servicos")
	private String dataSincronismoServicos;

	public ClienteRjConsultores() {
	}

	public ClienteRjConsultores(Cliente cliente, String codConexao, String codCliente, String dataEnvio,
			String dataSincronismoServicos) {
		this.cliente = cliente;
		this.codConexao = codConexao;
		this.codCliente = codCliente;
		this.dataEnvio = dataEnvio;
		this.dataSincronismoServicos = dataSincronismoServicos;
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
		if (dataEnvio == null) {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(new Date(System.currentTimeMillis() - 900 * 1000));
		} else {
			return dataEnvio;
		}
	}

	public void setDataEnvio(String dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public String getDataSincronismoServicos() {
		return dataSincronismoServicos;
	}

	public void setDataSincronismoServicos(String dataSincronismoServicos) {
		this.dataSincronismoServicos = dataSincronismoServicos;
	}

}