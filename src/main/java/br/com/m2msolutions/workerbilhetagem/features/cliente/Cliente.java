package br.com.m2msolutions.workerbilhetagem.features.cliente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_cliente")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCliente;

	@Column(name = "nm_nome")
	private String nmNome;

	@Column(name = "nm_worker")
	private String nmWorker;

	@Column(name = "ds_timezone")
	private String dsTimezone;

	@Column(name = "nm_worker_sinotico")
	private String nmWorkerSinotico;

	@Column(name = "fl_ativo")
	private String flAtivo;

	@Column(name = "url_zona")
	private String urlZona;

	@Column(name = "id_usuario")
	private String idUsuario;

	@Column(name = "url_login")
	private String urlLogin;

	@Column(name = "ip_redis")
	private String ipPedis;

	@Column(name = "cd_cnpj")
	private String cdCnpj;

	@Column(name = "nm_email")
	private String nmEmail;

	@Column(name = "nu_telefone")
	private String nuTelefone;

	@Column(name = "nm_contato")
	private String nmContato;

	@Column(name = "nu_usuarios")
	private String nuUsuarios;

	@Column(name = "fl_situacao")
	private String flSituacao;

	@Column(name = "ds_motivo")
	private String dsMotivo;

	@Column(name = "limite_veiculo_meuonibus")
	private String limiteVeiculoMeuonibus;

	@Column(name = "dt_ultima_alteracao")
	private String dtUltimaAlteracao;

	@Column(name = "nm_usuario_alteracao")
	private String nmUsuarioAlteracao;

	@Column(name = "email_meu_onibus")
	private String emailMeuOnibus;

	@Column(name = "fl_atualiza_cliente")
	private String flAtualizaCliente;

	@Column(name = "url_monitrip")
	private String urlMonitrip;

	@Column(name = "nu_direcao_continua")
	private String nuDirecaoContinua;

	@Column(name = "nu_jornada_trabalho")
	private String nuJornadaTrabalho;

	@OneToMany(cascade=CascadeType.ALL, targetEntity=ConsorcioCliente.class, fetch = FetchType.EAGER)
	@JoinColumn(name="id_cliente")
	private List<ConsorcioCliente> listaConsorcioCliente = new ArrayList<ConsorcioCliente>()  ;

	public List<ConsorcioCliente> getListaConsorcioCliente() {
		return listaConsorcioCliente;
	}

	public void setListaConsorcioCliente(List<ConsorcioCliente> listaConsorcioCliente) {
		this.listaConsorcioCliente = listaConsorcioCliente;
	}

	protected Cliente() {
	}

	public Cliente(Integer idCliente, String nmNome, String nmWorker, String dsTimezone, String nmWorkerSinotico,
			String flAtivo, String urlZona, String idUsuario, String urlLogin, String ipPedis, String cdCnpj,
			String nmEmail, String nuTelefone, String nmContato, String nuUsuarios, String flSituacao, String dsMotivo,
			String limiteVeiculoMeuonibus, String dtUltimaAlteracao, String nmUsuarioAlteracao, String emailMeuOnibus,
			String flAtualizaCliente, String urlMonitrip, String nuDirecaoContinua, String nuJornadaTrabalho) {
		this.idCliente = idCliente;
		this.nmNome = nmNome;
		this.nmWorker = nmWorker;
		this.dsTimezone = dsTimezone;
		this.nmWorkerSinotico = nmWorkerSinotico;
		this.flAtivo = flAtivo;
		this.urlZona = urlZona;
		this.idUsuario = idUsuario;
		this.urlLogin = urlLogin;
		this.ipPedis = ipPedis;
		this.cdCnpj = cdCnpj;
		this.nmEmail = nmEmail;
		this.nuTelefone = nuTelefone;
		this.nmContato = nmContato;
		this.nuUsuarios = nuUsuarios;
		this.flSituacao = flSituacao;
		this.dsMotivo = dsMotivo;
		this.limiteVeiculoMeuonibus = limiteVeiculoMeuonibus;
		this.dtUltimaAlteracao = dtUltimaAlteracao;
		this.nmUsuarioAlteracao = nmUsuarioAlteracao;
		this.emailMeuOnibus = emailMeuOnibus;
		this.flAtualizaCliente = flAtualizaCliente;
		this.urlMonitrip = urlMonitrip;
		this.nuDirecaoContinua = nuDirecaoContinua;
		this.nuJornadaTrabalho = nuJornadaTrabalho;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNmNome() {
		return nmNome;
	}

	public void setNmNome(String nmNome) {
		this.nmNome = nmNome;
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

	public String getFlAtivo() {
		return flAtivo;
	}

	public void setFlAtivo(String flAtivo) {
		this.flAtivo = flAtivo;
	}

	public String getUrlZona() {
		return urlZona;
	}

	public void setUrlZona(String urlZona) {
		this.urlZona = urlZona;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUrlLogin() {
		return urlLogin;
	}

	public void setUrlLogin(String urlLogin) {
		this.urlLogin = urlLogin;
	}

	public String getIpPedis() {
		return ipPedis;
	}

	public void setIpPedis(String ipPedis) {
		this.ipPedis = ipPedis;
	}

	public String getCdCnpj() {
		return cdCnpj;
	}

	public void setCdCnpj(String cdCnpj) {
		this.cdCnpj = cdCnpj;
	}

	public String getNmEmail() {
		return nmEmail;
	}

	public void setNmEmail(String nmEmail) {
		this.nmEmail = nmEmail;
	}

	public String getNuTelefone() {
		return nuTelefone;
	}

	public void setNuTelefone(String nuTelefone) {
		this.nuTelefone = nuTelefone;
	}

	public String getNmContato() {
		return nmContato;
	}

	public void setNmContato(String nmContato) {
		this.nmContato = nmContato;
	}

	public String getNuUsuarios() {
		return nuUsuarios;
	}

	public void setNuUsuarios(String nuUsuarios) {
		this.nuUsuarios = nuUsuarios;
	}

	public String getFlSituacao() {
		return flSituacao;
	}

	public void setFlSituacao(String flSituacao) {
		this.flSituacao = flSituacao;
	}

	public String getDsMotivo() {
		return dsMotivo;
	}

	public void setDsMotivo(String dsMotivo) {
		this.dsMotivo = dsMotivo;
	}

	public String getLimiteVeiculoMeuonibus() {
		return limiteVeiculoMeuonibus;
	}

	public void setLimiteVeiculoMeuonibus(String limiteVeiculoMeuonibus) {
		this.limiteVeiculoMeuonibus = limiteVeiculoMeuonibus;
	}

	public String getDtUltimaAlteracao() {
		return dtUltimaAlteracao;
	}

	public void setDtUltimaAlteracao(String dtUltimaAlteracao) {
		this.dtUltimaAlteracao = dtUltimaAlteracao;
	}

	public String getNmUsuarioAlteracao() {
		return nmUsuarioAlteracao;
	}

	public void setNmUsuarioAlteracao(String nmUsuarioAlteracao) {
		this.nmUsuarioAlteracao = nmUsuarioAlteracao;
	}

	public String getEmailMeuOnibus() {
		return emailMeuOnibus;
	}

	public void setEmailMeuOnibus(String emailMeuOnibus) {
		this.emailMeuOnibus = emailMeuOnibus;
	}

	public String getFlAtualizaCliente() {
		return flAtualizaCliente;
	}

	public void setFlAtualizaCliente(String flAtualizaCliente) {
		this.flAtualizaCliente = flAtualizaCliente;
	}

	public String getUrlMonitrip() {
		return urlMonitrip;
	}

	public void setUrlMonitrip(String urlMonitrip) {
		this.urlMonitrip = urlMonitrip;
	}

	public String getNuDirecaoContinua() {
		return nuDirecaoContinua;
	}

	public void setNuDirecaoContinua(String nuDirecaoContinua) {
		this.nuDirecaoContinua = nuDirecaoContinua;
	}

	public String getNuJornadaTrabalho() {
		return nuJornadaTrabalho;
	}

	public void setNuJornadaTrabalho(String nuJornadaTrabalho) {
		this.nuJornadaTrabalho = nuJornadaTrabalho;
	}

}
