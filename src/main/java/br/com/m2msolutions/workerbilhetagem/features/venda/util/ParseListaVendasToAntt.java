package br.com.m2msolutions.workerbilhetagem.features.venda.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import br.com.m2msolutions.workerbilhetagem.features.cliente.ClienteRjConsultores;
import br.com.m2msolutions.workerbilhetagem.features.cliente.ConsorcioCliente;
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

		logVendaPassagem.setIdLog(Integer.parseInt(venda.getIdLog()));
		logVendaPassagem.setCodigoBilheteEmbarque(venda.getIdentificadorBilhete());
		
		LOGGER.error(" CNPJ retorno RJ venda: {}", venda.getCnpj());
		LOGGER.error(" Valida CNPJ  RJ venda: {}", vendasUtil.isValidCNPJ(venda.getCnpj()));
		
		if (vendasUtil.isValidCNPJ(venda.getCnpj().trim())) {
			if(clienteRj.getCliente().getListaConsorcioCliente().isEmpty()) {
				logVendaPassagem.setCnpjEmpresa(clienteRj.getCliente().getCdCnpj());
			}else {
				for(ConsorcioCliente consorcioCliente : clienteRj.getCliente().getListaConsorcioCliente()) {

					LOGGER.error(" Codigo Cliente(): {} - Total Consorcio: {}", clienteRj.getCliente().getIdCliente(), clienteRj.getCliente().getListaConsorcioCliente().size());
					
					LOGGER.error(" CNPJ Consorcio Empresa  getCnpjEmpresa: {}", consorcioCliente.getCnpjEmpresa());
					LOGGER.error(" CNPJ Consorcio  getCnpjConsorcio : {}", consorcioCliente.getCnpjConsorcio());
					
					Long cnpjEmpresa = Long.valueOf(venda.getCnpj());
					Long cnpjConsorcio = Long.valueOf(consorcioCliente.getCnpjEmpresa()); 
					
					if(cnpjEmpresa.equals(cnpjConsorcio)) {
						LOGGER.error(" Entrou em : {}", cnpjEmpresa.equals(cnpjConsorcio));
						logVendaPassagem.setCnpjEmpresa(consorcioCliente.getCnpjConsorcio());
						break;
					}
					
					if (venda.getCnpj().trim().equals(consorcioCliente.getCnpjEmpresa())) {
						logVendaPassagem.setCnpjEmpresa(consorcioCliente.getCnpjConsorcio());
						break;
					}	
				}
				if(logVendaPassagem.getCnpjEmpresa() == null) {
					logVendaPassagem.setCnpjEmpresa(clienteRj.getCliente().getCdCnpj());
				}
			}
		} else {
			LOGGER.error(" CNPJ retorno RJ venda: {}", venda.getCnpj());
			
			LOGGER.error("CNPJ Invalido: {}", clienteRj.getCliente().getCdCnpj());
		}
		
		LOGGER.error("CNPJ Empresa ( logVendaPassagem.getCnpjEmpresa()  ): {}", logVendaPassagem.getCnpjEmpresa());
		
//		if (vendasUtil.isValidCNPJ(clienteRj.getCliente().getCdCnpj())) {
//			logVendaPassagem.setCnpjEmpresa(clienteRj.getCliente().getCdCnpj());
//		} else {
//			LOGGER.error("CNPJ Invalido: {}", clienteRj.getCliente().getCdCnpj());
//		}

		logVendaPassagem.setNumeroSerieEquipamentoFiscal(venda.getNumSerie());
		logVendaPassagem.setNumeroBilheteEmbarque(venda.getNumBilheteEmbarque());
		logVendaPassagem.setDataEmissaoBilhete(vendasUtil.parseStringDateToUTC(venda.getDataEmissao()));
		logVendaPassagem.setHoraEmissaoBilhete(vendasUtil.parseStringHourToUTC(venda.getHoraEmissao()));
		logVendaPassagem.setCodigoCategoriaTransporte(venda.getCategoria());
		logVendaPassagem.setIdentificacaoLinha(venda.getLinha());
		logVendaPassagem.setIdPontoOrigemViagem("".equals(venda.getOrigem()) ? "0" : venda.getOrigem());
		logVendaPassagem.setIdPontoDestinoViagem("".equals(venda.getDestino()) ? "0" : venda.getDestino());
		logVendaPassagem.setCodigoTipoServico(venda.getTipoServico());
		logVendaPassagem.setDataViagem(vendasUtil.parseStringDateToUTC(venda.getDataViagem()));
		logVendaPassagem.setHoraViagem(vendasUtil.parseStringHourToUTC(venda.getHoraViagem()));
		logVendaPassagem.setCodigoTipoViagem(Integer.parseInt(venda.getTipoViagem()));
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
/*
		if(venda.getStatus() != 0) {
			LogCancelamentoPassagem logCancelamentoPassagem = new LogCancelamentoPassagem();

			logCancelamentoPassagem.setIdLog(Integer.parseInt(venda.getIdLog()));
			logCancelamentoPassagem.setNumeroBilheteEmbarque(venda.getNumBilheteEmbarque());
			logCancelamentoPassagem.setIdentificacaoLinha(venda.getLinha());
			logCancelamentoPassagem.setDataViagem(vendasUtil.parseStringDateToUTC(venda.getDataViagem()));
			logCancelamentoPassagem.setHoraViagem(vendasUtil.parseStringHourToUTC(venda.getHoraViagem()));
			logCancelamentoPassagem.setCodigoMotivoCancelamento(venda.getStatus());
			logCancelamentoPassagem.setDataHoraCancelamento(venda.getDataEmissao());
			logCancelamentoPassagem.setNumeroNovoBilheteEmbarque(venda.getNumeroNovoBilheteEmbarque());
			logVendaPassagem.setLogCancelamentoPassagem(logCancelamentoPassagem);
		}
*/
		Gson gson = new Gson();
		String listaVendasJson = gson.toJson(logVendaPassagem);

		return listaVendasJson.toString();
	}


}
