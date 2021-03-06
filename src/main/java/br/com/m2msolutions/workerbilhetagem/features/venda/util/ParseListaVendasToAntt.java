package br.com.m2msolutions.workerbilhetagem.features.venda.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import br.com.m2msolutions.workerbilhetagem.features.cliente.ClienteRjConsultores;
import br.com.m2msolutions.workerbilhetagem.features.cliente.ConsorcioCliente;
import br.com.m2msolutions.workerbilhetagem.features.venda.model.InformacoesPassageiro;
import br.com.m2msolutions.workerbilhetagem.features.venda.model.LogCancelamentoPassagem;
import br.com.m2msolutions.workerbilhetagem.features.venda.model.LogVendaPassagem;
import br.com.m2msolutions.workerbilhetagem.features.venda.model.Venda;

@Component
public class ParseListaVendasToAntt {
	private Logger LOGGER = LoggerFactory.getLogger(ParseListaVendasToAntt.class);

	private static final  String NUM_SERIE_FIXO = "000013";
	private static  final String ID_LOG_CANCELAMENTO = "11";

	@Autowired
	private VendasUtil vendasUtil;

public synchronized String parseCancelamento(Venda venda, String cancelamentoId, String transacaoId) {
		
		Gson gson = new Gson();
		

			LogCancelamentoPassagem logCancelamentoPassagem = new LogCancelamentoPassagem();

            logCancelamentoPassagem.setIdLog(Integer.parseInt(cancelamentoId));

            if((null == venda.getNumBilheteEmbarque() || "".equals(venda.getNumBilheteEmbarque()))) {
                logCancelamentoPassagem.setNumeroBilheteEmbarque(vendasUtil.completaComZerosEsquerda(9, venda.getNumBilheteEstado()));
            }else {
                logCancelamentoPassagem.setNumeroBilheteEmbarque(venda.getNumBilheteEmbarque());
            }

            logCancelamentoPassagem.setIdentificacaoLinha(vendasUtil.onlyNumbersFormat(venda.getLinha()));
			logCancelamentoPassagem.setDataViagem(vendasUtil.parseStringDateToUTC(venda.getDataViagem()));
			logCancelamentoPassagem.setHoraViagem(vendasUtil.parseStringHourToUTC(venda.getHoraViagem()));
			logCancelamentoPassagem.setCodigoMotivoCancelamento(venda.getStatus());
			logCancelamentoPassagem.setDataHoraCancelamento(vendasUtil.parseStringToSqlDate(venda.getDataEmissao(), venda.getHoraEmissao()).replaceAll(" ", "T"));
			logCancelamentoPassagem.setNumeroNovoBilheteEmbarque(venda.getNumeroNovoBilheteEmbarque());
			if(transacaoId != null)
				logCancelamentoPassagem.setTransacaoId(transacaoId);


			return gson.toJson(logCancelamentoPassagem).toString();
	}

public synchronized String parse(Venda venda, ClienteRjConsultores clienteRj,String transacaoId) {
		
		
		LogVendaPassagem logVendaPassagem = new LogVendaPassagem();

		logVendaPassagem.setIdLog(Integer.parseInt(venda.getIdLog()));
		logVendaPassagem.setCodigoBilheteEmbarque(venda.getIdentificadorBilhete());
		
		//TODO Retir após levantamento dos dados de cancelamento para otimização
		if(ID_LOG_CANCELAMENTO.equals(venda.getIdLog())) {
			logVendaPassagem.setCodigoMotivoCancelamento(venda.getStatus());
			logVendaPassagem.setDataHoraCancelamento(vendasUtil.parseStringToSqlDate(venda.getDataEmissao(), venda.getHoraEmissao()).replaceAll(" ", "T"));
			logVendaPassagem.setNumeroNovoBilheteEmbarque(venda.getNumeroNovoBilheteEmbarque());
		}

		
		if (vendasUtil.isValidCNPJ(venda.getCnpj().trim())) {

			if(clienteRj.getCliente().getListaConsorcioCliente().isEmpty()) {
				LOGGER.info(" Cliente: {} - Lista Vazia ", clienteRj.getCliente().getIdCliente());
				LOGGER.info(" CNPJ retorno RJ: {} - Lista Vazia ", venda.getCnpj());
				logVendaPassagem.setCnpjEmpresa(clienteRj.getCliente().getCdCnpj());

			}else {
				for(ConsorcioCliente consorcioCliente : clienteRj.getCliente().getListaConsorcioCliente()) {
					
					Long cnpjEmpresa = Long.valueOf(venda.getCnpj());
					Long cnpjConsorcio = Long.valueOf(consorcioCliente.getCnpjEmpresa()); 
					
					
					if(cnpjEmpresa.equals(cnpjConsorcio)) {

						logVendaPassagem.setCnpjEmpresa(consorcioCliente.getCnpjConsorcio());
						break;
					}
					
				}

				if(logVendaPassagem.getCnpjEmpresa() == null) {
					LOGGER.info(" CNPJ logVendaPassagem.getCnpjEmpresa(): {}", logVendaPassagem.getCnpjEmpresa());
					LOGGER.info(" Codigo Cliente: {} ", clienteRj.getCliente().getIdCliente());
					LOGGER.info(" CNPJ retorno RJ: {}", venda.getCnpj());
					logVendaPassagem.setCnpjEmpresa(clienteRj.getCliente().getCdCnpj());
				}
			}
		} else {
			LOGGER.error(" CNPJ retorno RJ venda: {}", venda.getCnpj());
			
			LOGGER.error("CNPJ Invalido: {}", clienteRj.getCliente().getCdCnpj());
		}

        if ((null == venda.getNumSerie() || "0".equals(venda.getNumSerie())) && (null == venda.getNumBilheteEmbarque() || "".equals(venda.getNumBilheteEmbarque())) && null != venda.getNumBilheteImpresso()){

            logVendaPassagem.setNumeroSerieEquipamentoFiscal(NUM_SERIE_FIXO);
            logVendaPassagem.setNumeroBilheteEmbarque(vendasUtil.completaComZerosEsquerda(9, venda.getNumBilheteEstado()));
        }else{
            logVendaPassagem.setNumeroSerieEquipamentoFiscal(venda.getNumSerie());
            logVendaPassagem.setNumeroBilheteEmbarque(venda.getNumBilheteEmbarque());
        }

        if((null == venda.getNumBilheteEmbarque() || "".equals(venda.getNumBilheteEmbarque()))) {
            logVendaPassagem.setNumeroBilheteEmbarque(vendasUtil.completaComZerosEsquerda(9, venda.getNumBilheteEstado()));
        }else {
            logVendaPassagem.setNumeroBilheteEmbarque(venda.getNumBilheteEmbarque());
        }
		logVendaPassagem.setDataEmissaoBilhete(vendasUtil.parseStringDateToUTC(venda.getDataEmissao()));
		logVendaPassagem.setHoraEmissaoBilhete(vendasUtil.parseStringHourToUTC(venda.getHoraEmissao()));
		logVendaPassagem.setCodigoCategoriaTransporte(venda.getCategoria());
		logVendaPassagem.setIdentificacaoLinha(vendasUtil.onlyNumbersFormat(venda.getLinha()));
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
		
		Gson gson = new Gson();
		String listaVendasJson = gson.toJson(logVendaPassagem);

		return listaVendasJson.toString();
	}


}
