package br.com.m2msolutions.workerbilhetagem.features.venda.util;

import com.google.gson.Gson;

import br.com.m2msolutions.workerbilhetagem.features.venda.model.LogVendaPassagemModel;
import br.com.m2msolutions.workerbilhetagem.features.venda.model.VendaModel;

public class ParseListaVendasToAntt {
	public static String parse(VendaModel venda) {
		LogVendaPassagemModel logVendaPassagem = new LogVendaPassagemModel();

		logVendaPassagem.setIdLog(venda.getIdLog());
		logVendaPassagem.setCodigoBilheteEmbarque(venda.getNumBilheteEmbarque());
		logVendaPassagem.setCnpjEmpresa(venda.getCnpj());
		logVendaPassagem.setNumeroSerieEquipamentoFiscal(venda.getNumSerie());
		logVendaPassagem.setNumeroBilheteEmbarque(venda.getNumBilheteEmbarque());
		logVendaPassagem.setDataEmissaoBilhete(venda.getDataEmissao());
		logVendaPassagem.setHoraEmissaoBilhete(venda.getHoraEmissao());
		logVendaPassagem.setCodigoCategoriaTransporte(venda.getCategoria());
		logVendaPassagem.setIdentificacaoLinha(venda.getLinha());
		logVendaPassagem.setIdPontoOrigemViagem(venda.getOrigem());
		logVendaPassagem.setIdPontoDestinoViagem(venda.getDestino());
		logVendaPassagem.setCodigoTipoServico(venda.getTipoServico());
		logVendaPassagem.setDataViagem(venda.getDataViagem());
		logVendaPassagem.setHoraViagem(venda.getHoraVigem());
		// logVendaPassagem.setCodigoTipoViagem(codigoTipoViagem);
		// logVendaPassagem.setNumeroPoltrona(numeroPoltrona);
		logVendaPassagem.setPlataformaEmbarque(venda.getPlataforma());
		// logVendaPassagem.setCodigoMotivoDesconto(codigoMotivoDesconto);
		logVendaPassagem.setValorTarifa(venda.getTarifa());
		logVendaPassagem.setPercentualDesconto(venda.getPerDesconto());
		logVendaPassagem.setAliquotaICMS(venda.getAliquotaICMS());
		logVendaPassagem.setValorPedagio(venda.getValorPedagio());
		logVendaPassagem.setValorTaxaEmbarque(venda.getTaxaEmbarque());
		logVendaPassagem.setValorTotal(venda.getValorTotal());
		logVendaPassagem.setNomePassageiro(venda.getNomePassageiro());
		logVendaPassagem.setDocumentoIdentificacaoPassageiro(venda.getDocPassageiro());
		logVendaPassagem.setCpfPassageiro(venda.getCpfPassageiro());
		logVendaPassagem.setCelularPassageiro(venda.getCelularPassageiro());
		logVendaPassagem.setOrigemEmissao(venda.getOrigemEmissao());

		Gson gson = new Gson();
		String listaVendasJson = gson.toJson(venda);

		return listaVendasJson.toString();
	}
}
