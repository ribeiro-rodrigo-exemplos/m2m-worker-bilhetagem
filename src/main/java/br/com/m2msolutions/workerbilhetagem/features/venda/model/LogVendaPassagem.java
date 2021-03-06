package br.com.m2msolutions.workerbilhetagem.features.venda.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LogVendaPassagem {

	public Integer getCodigoMotivoCancelamento() {
		return codigoMotivoCancelamento;
	}

	public void setCodigoMotivoCancelamento(Integer codigoMotivoCancelamento) {
		this.codigoMotivoCancelamento = codigoMotivoCancelamento;
	}

	public String getDataHoraCancelamento() {
		return dataHoraCancelamento;
	}

	public void setDataHoraCancelamento(String dataHoraCancelamento) {
		this.dataHoraCancelamento = dataHoraCancelamento;
	}

	public String getNumeroNovoBilheteEmbarque() {
		return numeroNovoBilheteEmbarque;
	}

	public void setNumeroNovoBilheteEmbarque(String numeroNovoBilheteEmbarque) {
		this.numeroNovoBilheteEmbarque = numeroNovoBilheteEmbarque;
	}

	private Integer idLog;
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
	private Integer codigoTipoViagem;
	private String numeroPoltrona;
	private String plataformaEmbarque;
	private String codigoMotivoDesconto;
	private String valorTarifa;
	private String percentualDesconto;
	private String aliquotaICMS;
	private String valorPedagio;
	private String valorTaxaEmbarque;
	private String valorTotal;
	private String transacaoId;
	private InformacoesPassageiro informacoesPassageiro;

    private String numBilheteImpresso;
    private String numServico;
    private Integer status;
    private String dataHoraEvento;

	private String origemEmissao;
	
    private Integer codigoMotivoCancelamento;
    private String dataHoraCancelamento;
    private String numeroNovoBilheteEmbarque;


	public String getTransacaoId() {
		return transacaoId;
	}

	public void setTransacaoId(String transacaoId) {
		this.transacaoId = transacaoId;
	}

	public Integer getIdLog() {
		return idLog;
	}

    public String getNumBilheteImpresso() {
        return numBilheteImpresso;
    }

    public void setNumBilheteImpresso(String numBilheteImpresso) {
        this.numBilheteImpresso = numBilheteImpresso;
    }

    public String getNumServico() {
        return numServico;
    }

    public void setNumServico(String numServico) {
        this.numServico = numServico;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDataHoraEvento() {
        return dataHoraEvento;
    }

    public void setDataHoraEvento(String dataHoraEvento) {

	    try{

            Date data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataHoraEvento);
            this.dataHoraEvento = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(data);

        }catch (ParseException e){
            throw new RuntimeException(e);
        }
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setIdLog(Integer idLog) {
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
		this.numeroSerieEquipamentoFiscal = numeroSerieEquipamentoFiscal == null ? "0":numeroSerieEquipamentoFiscal;
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
		this.identificacaoLinha = identificacaoLinha != null ? identificacaoLinha.replace("-",""):"0000";
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

	public Integer getCodigoTipoViagem() {
		return codigoTipoViagem;
	}

	public void setCodigoTipoViagem(Integer codigoTipoViagem) {
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
		this.percentualDesconto = percentualDesconto == null ? "0.0" : percentualDesconto;
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

	    this.valorPedagio = valorPedagio == null ? "0.0" : valorPedagio;
	}

	public String getValorTaxaEmbarque() {
		return valorTaxaEmbarque;
	}

	public void setValorTaxaEmbarque(String valorTaxaEmbarque) {
		this.valorTaxaEmbarque = valorTaxaEmbarque == null ? "0.0":valorTaxaEmbarque;
	}

	public String getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal == null ? "0":valorTotal;
	}

	public String getOrigemEmissao() {
		return origemEmissao;
	}

	public void setOrigemEmissao(String origemEmissao) {
		this.origemEmissao = origemEmissao;
	}

	public InformacoesPassageiro getInformacoesPassageiro() {
		return informacoesPassageiro;
	}

	public void setInformacoesPassageiro(InformacoesPassageiro informacoesPassageiro) {
		this.informacoesPassageiro = informacoesPassageiro;
	}
}