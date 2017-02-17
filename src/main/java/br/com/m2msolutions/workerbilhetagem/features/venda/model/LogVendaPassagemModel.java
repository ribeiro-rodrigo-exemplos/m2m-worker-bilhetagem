package br.com.m2msolutions.workerbilhetagem.features.venda.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@XmlRootElement(name = "venda")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LogVendaPassagemModel {

	private String idLog;
	private String codigoBilheteEmbarque;
	private String cnpjEmpresa;
	private String numeroSerieEquipamentoFiscal;
	private String numeroBilheteEmbarque;
	private String dataEmissaoBilhete;
	private String horaEmissaoBilhete;
	private String codigoCategoriaTransporte;
	private String identificacaoLinha;
	private String idPontoOrigemViagem;
	private String idPontoDestinoViagem;
	private String codigoTipoServico;
	private String dataViagem;
	private String horaViagem;
	private String codigoTipoViagem;
	private String numeroPoltrona;
	private String plataformaEmbarque;
	private String codigoMotivoDesconto;
	private String valorTarifa;
	private String percentualDesconto;
	private String aliquotaICMS;
	private String valorPedagio;
	private String valorTaxaEmbarque;
	private String valorTotal;
	private String nomePassageiro;
	private String documentoIdentificacaoPassageiro;
	private String cpfPassageiro;
	private String celularPassageiro;
	private String origemEmissao;

	public String getIdLog() {
		return idLog;
	}

	public void setIdLog(String idLog) {
		this.idLog = idLog;
	}

	public String getCodigoBilheteEmbarque() {
		return codigoBilheteEmbarque;
	}

	public void setCodigoBilheteEmbarque(String codigoBilheteEmbarque) {
		this.codigoBilheteEmbarque = codigoBilheteEmbarque;
	}

	public String getCnpjEmpresa() {
		return cnpjEmpresa;
	}

	public void setCnpjEmpresa(String cnpjEmpresa) {
		this.cnpjEmpresa = cnpjEmpresa;
	}

	public String getNumeroSerieEquipamentoFiscal() {
		return numeroSerieEquipamentoFiscal;
	}

	public void setNumeroSerieEquipamentoFiscal(String numeroSerieEquipamentoFiscal) {
		this.numeroSerieEquipamentoFiscal = numeroSerieEquipamentoFiscal;
	}

	public String getNumeroBilheteEmbarque() {
		return numeroBilheteEmbarque;
	}

	public void setNumeroBilheteEmbarque(String numeroBilheteEmbarque) {
		this.numeroBilheteEmbarque = numeroBilheteEmbarque;
	}

	public String getDataEmissaoBilhete() {
		return dataEmissaoBilhete;
	}

	public void setDataEmissaoBilhete(String dataEmissaoBilhete) {
		this.dataEmissaoBilhete = dataEmissaoBilhete;
	}

	public String getHoraEmissaoBilhete() {
		return horaEmissaoBilhete;
	}

	public void setHoraEmissaoBilhete(String horaEmissaoBilhete) {
		this.horaEmissaoBilhete = horaEmissaoBilhete;
	}

	public String getCodigoCategoriaTransporte() {
		return codigoCategoriaTransporte;
	}

	public void setCodigoCategoriaTransporte(String codigoCategoriaTransporte) {
		this.codigoCategoriaTransporte = codigoCategoriaTransporte;
	}

	public String getIdentificacaoLinha() {
		return identificacaoLinha;
	}

	public void setIdentificacaoLinha(String identificacaoLinha) {
		this.identificacaoLinha = identificacaoLinha;
	}

	public String getIdPontoOrigemViagem() {
		return idPontoOrigemViagem;
	}

	public void setIdPontoOrigemViagem(String idPontoOrigemViagem) {
		this.idPontoOrigemViagem = idPontoOrigemViagem;
	}

	public String getIdPontoDestinoViagem() {
		return idPontoDestinoViagem;
	}

	public void setIdPontoDestinoViagem(String idPontoDestinoViagem) {
		this.idPontoDestinoViagem = idPontoDestinoViagem;
	}

	public String getCodigoTipoServico() {
		return codigoTipoServico;
	}

	public void setCodigoTipoServico(String codigoTipoServico) {
		this.codigoTipoServico = codigoTipoServico;
	}

	public String getDataViagem() {
		return dataViagem;
	}

	public void setDataViagem(String dataViagem) {
		this.dataViagem = dataViagem;
	}

	public String getHoraViagem() {
		return horaViagem;
	}

	public void setHoraViagem(String horaViagem) {
		this.horaViagem = horaViagem;
	}

	public String getCodigoTipoViagem() {
		return codigoTipoViagem;
	}

	public void setCodigoTipoViagem(String codigoTipoViagem) {
		this.codigoTipoViagem = codigoTipoViagem;
	}

	public String getNumeroPoltrona() {
		return numeroPoltrona;
	}

	public void setNumeroPoltrona(String numeroPoltrona) {
		this.numeroPoltrona = numeroPoltrona;
	}

	public String getPlataformaEmbarque() {
		return plataformaEmbarque;
	}

	public void setPlataformaEmbarque(String plataformaEmbarque) {
		this.plataformaEmbarque = plataformaEmbarque;
	}

	public String getCodigoMotivoDesconto() {
		return codigoMotivoDesconto;
	}

	public void setCodigoMotivoDesconto(String codigoMotivoDesconto) {
		this.codigoMotivoDesconto = codigoMotivoDesconto;
	}

	public String getValorTarifa() {
		return valorTarifa;
	}

	public void setValorTarifa(String valorTarifa) {
		this.valorTarifa = valorTarifa;
	}

	public String getPercentualDesconto() {
		return percentualDesconto;
	}

	public void setPercentualDesconto(String percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}

	public String getAliquotaICMS() {
		return aliquotaICMS;
	}

	public void setAliquotaICMS(String aliquotaICMS) {
		this.aliquotaICMS = aliquotaICMS;
	}

	public String getValorPedagio() {
		return valorPedagio;
	}

	public void setValorPedagio(String valorPedagio) {
		this.valorPedagio = valorPedagio;
	}

	public String getValorTaxaEmbarque() {
		return valorTaxaEmbarque;
	}

	public void setValorTaxaEmbarque(String valorTaxaEmbarque) {
		this.valorTaxaEmbarque = valorTaxaEmbarque;
	}

	public String getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}

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

	public String getOrigemEmissao() {
		return origemEmissao;
	}

	public void setOrigemEmissao(String origemEmissao) {
		this.origemEmissao = origemEmissao;
	}
}