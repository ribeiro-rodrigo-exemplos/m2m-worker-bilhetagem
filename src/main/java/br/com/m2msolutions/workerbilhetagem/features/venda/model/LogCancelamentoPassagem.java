package br.com.m2msolutions.workerbilhetagem.features.venda.model;

/**
 * Created by rodrigo on 13/06/18.
 */
public class LogCancelamentoPassagem {
    private Integer idLog;
    private String numeroBilheteEmbarque;
    private String identificacaoLinha;
    private String dataViagem;
    private String horaViagem;
    private Integer codigoMotivoCancelamento;
    private String dataHoraCancelamento;
    private String numeroNovoBilheteEmbarque;

    public Integer getIdLog() {
        return idLog;
    }

    public void setIdLog(Integer idLog) {
        this.idLog = idLog;
    }

    public String getNumeroBilheteEmbarque() {
        return numeroBilheteEmbarque;
    }

    public void setNumeroBilheteEmbarque(String numeroBilheteEmbarque) {
        this.numeroBilheteEmbarque = numeroBilheteEmbarque;
    }

    public String getIdentificacaoLinha() {
        return identificacaoLinha;
    }

    public void setIdentificacaoLinha(String identificacaoLinha) {
        this.identificacaoLinha = identificacaoLinha;
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
}
