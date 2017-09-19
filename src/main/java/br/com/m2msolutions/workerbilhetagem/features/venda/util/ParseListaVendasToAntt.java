package br.com.m2msolutions.workerbilhetagem.features.venda.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import br.com.m2msolutions.workerbilhetagem.features.cliente.ClienteRjConsultores;
import br.com.m2msolutions.workerbilhetagem.features.venda.model.InformacoesPassageiro;
import br.com.m2msolutions.workerbilhetagem.features.venda.model.LogVendaPassagem;
import br.com.m2msolutions.workerbilhetagem.features.venda.model.Venda;

@Component
public class ParseListaVendasToAntt {
	private Logger LOGGER = LoggerFactory.getLogger(ParseListaVendasToAntt.class);

	@Autowired
	private VendasUtil vendasUtil;

	public String parse(Venda venda, ClienteRjConsultores clienteRj,String transacaoId) {
		LogVendaPassagem logVendaPassagem = new LogVendaPassagem();

		logVendaPassagem.setIdLog(venda.getIdLog());
		logVendaPassagem.setCodigoBilheteEmbarque(venda.getIdentificadorBilhete());

		if (vendasUtil.isValidCNPJ(clienteRj.getCliente().getCdCnpj())) {
			logVendaPassagem.setCnpjEmpresa(clienteRj.getCliente().getCdCnpj());
		} else {
			LOGGER.error("CNPJ Invalido: {}", clienteRj.getCliente().getCdCnpj());
		}

		logVendaPassagem.setNumeroSerieEquipamentoFiscal(venda.getNumSerie());
		logVendaPassagem.setNumeroBilheteEmbarque(
				("000000" + (venda.getNumBilheteEmbarque() != null ? venda.getNumBilheteEmbarque() : "0")).substring(
						(venda.getNumBilheteEmbarque() != null ? venda.getNumBilheteEmbarque().length() : 0)));
		logVendaPassagem.setDataEmissaoBilhete(vendasUtil.parseStringDateToUTC(venda.getDataEmissao()));
		logVendaPassagem.setHoraEmissaoBilhete(vendasUtil.parseStringHourToUTC(venda.getHoraEmissao()));
		logVendaPassagem.setCodigoCategoriaTransporte(venda.getCategoria());
		logVendaPassagem.setIdentificacaoLinha(venda.getLinha());
		logVendaPassagem.setIdPontoOrigemViagem("".equals(venda.getOrigem()) ? "0" : venda.getOrigem());
		logVendaPassagem.setIdPontoDestinoViagem("".equals(venda.getDestino()) ? "0" : venda.getDestino());
		logVendaPassagem.setCodigoTipoServico(venda.getTipoServico());
		logVendaPassagem.setDataViagem(vendasUtil.parseStringDateToUTC(venda.getDataViagem()));
		logVendaPassagem.setHoraViagem(vendasUtil.parseStringHourToUTC(venda.getHoraViagem()));
		logVendaPassagem.setCodigoTipoViagem(venda.getTipoViagem());
		logVendaPassagem.setNumeroPoltrona(venda.getPoltrona());
		logVendaPassagem.setPlataformaEmbarque(venda.getPlataforma());
		logVendaPassagem.setCodigoMotivoDesconto(venda.getMotivoDesconto());
		logVendaPassagem.setValorTarifa(venda.getTarifa());
		logVendaPassagem.setPercentualDesconto(venda.getPerDesconto());
		logVendaPassagem.setAliquotaICMS(venda.getAliquotaICMS());
		logVendaPassagem.setValorPedagio(venda.getValorPedagio());
		logVendaPassagem.setValorTaxaEmbarque(venda.getTaxaEmbarque());
		logVendaPassagem.setValorTotal(venda.getValorTotal());
		logVendaPassagem.setOrigemEmissao(venda.getOrigemEmissao());
		logVendaPassagem.setNumBilheteImpresso(venda.getNumBilheteImpresso());
		logVendaPassagem.setNumServico(venda.getNumServico());
		logVendaPassagem.setStatus(venda.getStatus());
		logVendaPassagem.setDataHoraEvento(clienteRj.getDataEnvio());
		if(transacaoId != null)
			logVendaPassagem.setTransacaoId(transacaoId);

		InformacoesPassageiro infoPassageiro = new InformacoesPassageiro();

		infoPassageiro.setNomePassageiro(venda.getNomePassageiro().trim());
		infoPassageiro.setDocumentoIdentificacaoPassageiro(venda.getDocPassageiro());
		infoPassageiro
				.setCpfPassageiro(vendasUtil.isValidCPF(venda.getCpfPassageiro()) ? venda.getCpfPassageiro() : null);
		infoPassageiro.setCelularPassageiro(
				venda.getCelularPassageiro().length() >= 10 && venda.getCelularPassageiro().length() <= 14
						? venda.getCelularPassageiro() : null);

		logVendaPassagem.setInformacoesPassageiro(infoPassageiro);

		Gson gson = new Gson();
		String listaVendasJson = gson.toJson(logVendaPassagem);

		return listaVendasJson.toString();
	}


}
