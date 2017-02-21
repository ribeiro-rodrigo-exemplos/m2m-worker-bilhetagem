package br.com.m2msolutions.workerbilhetagem.features.venda.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import br.com.m2msolutions.workerbilhetagem.commom.Const;
import br.com.m2msolutions.workerbilhetagem.features.cliente.ClienteRjConsultores;
import br.com.m2msolutions.workerbilhetagem.features.venda.model.ListaVendasModel;

@Component
public class VendasUtil {
	private Logger LOGGER = LoggerFactory.getLogger(VendasUtil.class);

	private static String methodName = "buscaVendas";

	private static final int[] pesoCPF = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };
	private static final int[] pesoCNPJ = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

	public String createUrl(ClienteRjConsultores clienteRj) {
		Integer codConexao = Integer.parseInt(clienteRj.getCodConexao());
		String codEmpresa = clienteRj.getCodCliente();
		String data = parseDate(clienteRj.getDataEnvio());
		String hora = parseHour(clienteRj.getDataEnvio());

		StringBuilder url = new StringBuilder();

		url.append(Const.RjWebServiceUrl);
		url.append(methodName + "/");
		url.append(codConexao + "/");
		url.append(codEmpresa + "/");
		url.append(data + "/");
		url.append(hora);

		return url.toString();
	}

	private String parseDate(String dataCompleta) {
		String data = "";
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			DateFormat newDateFormat = new SimpleDateFormat("yyMMdd");

			Date date = dateFormat.parse(dataCompleta);

			data = newDateFormat.format(date);

		} catch (Exception e) {
			LOGGER.error("Error - " + e.toString());
		}
		return data;
	}

	private String parseHour(String dataCompleta) {
		String hora = "";

		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			DateFormat hourFormat = new SimpleDateFormat("HHmm");

			Date date = dateFormat.parse(dataCompleta);

			hora = hourFormat.format(date);

		} catch (Exception e) {
			LOGGER.error("Error - " + e.toString());
		}
		return hora;
	}

	public String parseStringToSqlDate(String date, String hour) {
		String dateTime = "";
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyMMdd HHmm");
			DateFormat hourFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			Date date_ = dateFormat.parse(date + " " + hour);

			dateTime = hourFormat.format(date_);

		} catch (Exception e) {
			LOGGER.error("Error - " + e.toString());
		}
		return dateTime;
	}

	public String getDataHoraUltimaVenda(ListaVendasModel listaVendas) {
		int ultimaVenda = listaVendas.getListaVendas().size() - 1;
		String dataUltimaVenda = listaVendas.getListaVendas().get(ultimaVenda).getDataEmissao();
		String horaUltimaVenda = listaVendas.getListaVendas().get(ultimaVenda).getHoraEmissao();

		return parseStringToSqlDate(dataUltimaVenda, horaUltimaVenda);
	}

	public String parseStringDateToUTC(String date) {
		String fullDate = "";
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyMMdd");
			DateFormat newDateFormat = new SimpleDateFormat("yyyyMMdd");

			Date date_ = dateFormat.parse(date);

			fullDate = newDateFormat.format(date_);

		} catch (Exception e) {
			LOGGER.error("Error - " + e.toString());
		}
		return fullDate;
	}

	public String parseStringHourToUTC(String hour) {
		String fullHour = "";
		try {
			DateFormat hourFormat = new SimpleDateFormat("HHmm");
			DateFormat newHourFormat = new SimpleDateFormat("HHmmss");

			Date date_ = hourFormat.parse(hour);

			fullHour = newHourFormat.format(date_);

		} catch (Exception e) {
			LOGGER.error("Error - " + e.toString());
		}
		return fullHour;
	}

	private int calcularDigito(String str, int[] peso) {
		int soma = 0;
		for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
			digito = Integer.parseInt(str.substring(indice, indice + 1));
			soma += digito * peso[peso.length - str.length() + indice];
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}

	public boolean isValidCPF(String cpf) {
		if ((cpf == null) || (cpf.length() != 11))
			return false;

		Integer digito1 = calcularDigito(cpf.substring(0, 9), pesoCPF);
		Integer digito2 = calcularDigito(cpf.substring(0, 9) + digito1, pesoCPF);
		return cpf.equals(cpf.substring(0, 9) + digito1.toString() + digito2.toString());
	}

	public boolean isValidCNPJ(String cnpj) {
		if ((cnpj == null) || (cnpj.length() != 14))
			return false;

		Integer digito1 = calcularDigito(cnpj.substring(0, 12), pesoCNPJ);
		Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, pesoCNPJ);
		return cnpj.equals(cnpj.substring(0, 12) + digito1.toString() + digito2.toString());
	}

}
