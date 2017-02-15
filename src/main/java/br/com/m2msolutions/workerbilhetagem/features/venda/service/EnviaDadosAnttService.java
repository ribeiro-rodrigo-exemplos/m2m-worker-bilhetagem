package br.com.m2msolutions.workerbilhetagem.features.venda.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.m2msolutions.workerbilhetagem.features.cliente.ClienteRjConsultores;
import br.com.m2msolutions.workerbilhetagem.features.venda.model.ListaVendasModel;
import br.com.m2msolutions.workerbilhetagem.features.venda.util.VendasUtil;

@Component
public class EnviaDadosAnttService {
	private Logger LOGGER = LoggerFactory.getLogger(EnviaDadosAnttService.class);

	@Autowired
	private VendasUtil vendasUtil;

	public void enviar(ListaVendasModel listaVendas, ClienteRjConsultores clienteRj) {
		LOGGER.info("Enviar Dados ANTT");
		LOGGER.info("Informacao enviada a ANTT - Cliente: {} - Ultima Venda: {}", clienteRj.getCliente().getNome(),
				getDataHoraUltimaVenda(listaVendas));
	}

	private String getDataHoraUltimaVenda(ListaVendasModel listaVendas) {
		int ultimaVenda = listaVendas.getListaVendas().size() - 1;
		String dataUltimaVenda = listaVendas.getListaVendas().get(ultimaVenda).getDataEmissao();
		String horaUltimaVenda = listaVendas.getListaVendas().get(ultimaVenda).getHoraEmissao();

		return vendasUtil.parseStringToSqlDate(dataUltimaVenda, horaUltimaVenda);
	}
}
