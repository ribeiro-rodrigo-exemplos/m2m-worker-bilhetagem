package br.com.m2msolutions.workerbilhetagem.features.venda;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@XmlRootElement(name = "venda")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class VendaModel {

	private String idLog;
	private String identificadorBilhete;
	private String cnpj;
	private String numSerie;
	private String numBilheteEmbarque;
	private String dataEmissao;
	private String horaEmissao;
	private String categoria;
	private String linha;
	private String origem;
	private String destino;
	private String tipoServico;
	private String dataViagem;
	private String horaVigem;
	private String tarifa;
	private String perDesconto;
	private String aliquotaICMS;
	private String valorPedagio;
	private String taxaEmbarque;
	private String valorTotal;
	private String nomePassageiro;
	private String docPassageiro;
	private String cpfPassageiro;
	private String celularPassageiro;
	private String numBilheteImpresso;
	private String numBilheteEstado;
	private String numServico;
	private String status;
	private String retorno;
	private String plataforma;
	private String origemEmissao;
	private String numeroNovoBilheteEmbarque;
	private String codRetorno;

	public String getIdLog() {
		return idLog;
	}

	public void setIdLog(String idLog) {
		this.idLog = idLog;
	}

	public String getIdentificadorBilhete() {
		return identificadorBilhete;
	}

	public void setIdentificadorBilhete(String identificadorBilhete) {
		this.identificadorBilhete = identificadorBilhete;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNumSerie() {
		return numSerie;
	}

	public void setNumSerie(String numSerie) {
		this.numSerie = numSerie;
	}

	public String getNumBilheteEmbarque() {
		return numBilheteEmbarque;
	}

	public void setNumBilheteEmbarque(String numBilheteEmbarque) {
		this.numBilheteEmbarque = numBilheteEmbarque;
	}

	public String getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public String getHoraEmissao() {
		return horaEmissao;
	}

	public void setHoraEmissao(String horaEmissao) {
		this.horaEmissao = horaEmissao;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getLinha() {
		return linha;
	}

	public void setLinha(String linha) {
		this.linha = linha;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}

	public String getDataViagem() {
		return dataViagem;
	}

	public void setDataViagem(String dataViagem) {
		this.dataViagem = dataViagem;
	}

	public String getHoraVigem() {
		return horaVigem;
	}

	public void setHoraVigem(String horaVigem) {
		this.horaVigem = horaVigem;
	}

	public String getTarifa() {
		return tarifa;
	}

	public void setTarifa(String tarifa) {
		this.tarifa = tarifa;
	}

	public String getPerDesconto() {
		return perDesconto;
	}

	public void setPerDesconto(String perDesconto) {
		this.perDesconto = perDesconto;
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

	public String getTaxaEmbarque() {
		return taxaEmbarque;
	}

	public void setTaxaEmbarque(String taxaEmbarque) {
		this.taxaEmbarque = taxaEmbarque;
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

	public String getDocPassageiro() {
		return docPassageiro;
	}

	public void setDocPassageiro(String docPassageiro) {
		this.docPassageiro = docPassageiro;
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

	public String getNumBilheteImpresso() {
		return numBilheteImpresso;
	}

	public void setNumBilheteImpresso(String numBilheteImpresso) {
		this.numBilheteImpresso = numBilheteImpresso;
	}

	public String getNumBilheteEstado() {
		return numBilheteEstado;
	}

	public void setNumBilheteEstado(String numBilheteEstado) {
		this.numBilheteEstado = numBilheteEstado;
	}

	public String getNumServico() {
		return numServico;
	}

	public void setNumServico(String numServico) {
		this.numServico = numServico;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRetorno() {
		return retorno;
	}

	public void setRetorno(String retorno) {
		this.retorno = retorno;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public String getOrigemEmissao() {
		return origemEmissao;
	}

	public void setOrigemEmissao(String origemEmissao) {
		this.origemEmissao = origemEmissao;
	}

	public String getNumeroNovoBilheteEmbarque() {
		return numeroNovoBilheteEmbarque;
	}

	public void setNumeroNovoBilheteEmbarque(String numeroNovoBilheteEmbarque) {
		this.numeroNovoBilheteEmbarque = numeroNovoBilheteEmbarque;
	}

	public String getCodRetorno() {
		return codRetorno;
	}

	public void setCodRetorno(String codRetorno) {
		this.codRetorno = codRetorno;
	}
}
