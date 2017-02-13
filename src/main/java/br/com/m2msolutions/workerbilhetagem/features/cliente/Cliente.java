package br.com.m2msolutions.workerbilhetagem.features.cliente;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_cliente")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idCliente;

	@Column(name = "nome")
	private String nome;

	@Column(name = "nm_worker")
	private String nmWorker;

	@Column(name = "ds_timezone")
	private String dsTimezone;

	@Column(name = "nm_worker_sinotico")
	private String nmWorkerSinotico;

	protected Cliente() {
	}

	public Cliente(String nome, String nmWorker, String dsTimezone, String nmWorkerSinotico) {
		this.nome = nome;
		this.nmWorker = nmWorker;
		this.nmWorker = nmWorker;
		this.nmWorkerSinotico = nmWorkerSinotico;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNmWorker() {
		return nmWorker;
	}

	public void setNmWorker(String nmWorker) {
		this.nmWorker = nmWorker;
	}

	public String getDsTimezone() {
		return dsTimezone;
	}

	public void setDsTimezone(String dsTimezone) {
		this.dsTimezone = dsTimezone;
	}

	public String getNmWorkerSinotico() {
		return nmWorkerSinotico;
	}

	public void setNmWorkerSinotico(String nmWorkerSinotico) {
		this.nmWorkerSinotico = nmWorkerSinotico;
	}
}
