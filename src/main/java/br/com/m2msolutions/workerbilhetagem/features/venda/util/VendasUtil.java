package br.com.m2msolutions.workerbilhetagem.features.venda.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import br.com.m2msolutions.workerbilhetagem.commom.Const;
import br.com.m2msolutions.workerbilhetagem.features.cliente.ClienteRjConsultores;

@Component
public class VendasUtil {
	private Logger LOGGER = LoggerFactory.getLogger(VendasUtil.class);

	private static String methodName = "buscaVendas";

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

}
