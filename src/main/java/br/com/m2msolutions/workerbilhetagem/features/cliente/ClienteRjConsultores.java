package br.com.m2msolutions.workerbilhetagem.features.cliente;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.persistence.*;

import com.sun.istack.NotNull;

@Entity
@Table(name = "rjconsultores")
public class ClienteRjConsultores {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;

	@NotNull
	@Column(name = "cod_conexao")
	private String codConexao;

	@NotNull
	@Column(name = "cod_cliente")
	private String codCliente;

	@NotNull
	@Column(name = "dt_sincronismo_venda_bilhetes")
	private String dataEnvio;

	@Transient
	private final static String datePattern = "yyyy-MM-dd HH:mm:ss";

	@Transient
	private final static String dateFormatPattern = "yyyy-MM-dd";

	public ClienteRjConsultores() {
	}

	public ClienteRjConsultores(Cliente cliente, String codConexao, String codCliente, String dataEnvio) {
		this.cliente = cliente;
		this.codConexao = codConexao;
		this.codCliente = codCliente;
		this.dataEnvio = dataEnvio;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getCodConexao() {
		return codConexao;
	}

	public void setCodConexao(String codConexao) {
		this.codConexao = codConexao;
	}

	public String getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}

	public String getDataEnvio() {
		if (dataEnvio == null) {
			return new SimpleDateFormat(datePattern)
					.format(new Date(System.currentTimeMillis() - 900 * 1000));
		} else {
			return dataEnvio;
		}
	}

	public void setNow(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MINUTE,calendar.get(Calendar.MINUTE)-5);
		dataEnvio = new SimpleDateFormat(datePattern).format(calendar.getTime());
	}

	public void nextMinute(){
		try{
			if(dataEnvio != null){
				Date data = new SimpleDateFormat(datePattern).parse(dataEnvio);

				//Timezone
				TimeZone tz = TimeZone.getTimeZone(cliente.getDsTimezone());
				TimeZone.setDefault(tz);
				Calendar calendar = Calendar.getInstance(tz);

				//Data  e hora com base no Timezone
				Date dateNow =  calendar.getTime();
				SimpleDateFormat sdfNow = new SimpleDateFormat(dateFormatPattern);
				String dateFormatNow = sdfNow.format(dateNow);
				int anoTZ = calendar.get(Calendar.YEAR);
				int mesTZ = calendar.get(Calendar.MONTH);
				int diaTZ = calendar.get(calendar.DAY_OF_MONTH);
				int horaTZ = calendar.get(Calendar.HOUR_OF_DAY);
				int minutosTZ = calendar.get(Calendar.MINUTE);

				//Data e hora com base no banco
				calendar.setTime(data);
				SimpleDateFormat sdfDataEnvio = new SimpleDateFormat(dateFormatPattern);
				String dateFormat = sdfDataEnvio.format(data);
				int horaBanco = calendar.get(Calendar.HOUR_OF_DAY);

				if(!dateFormat.equals(dateFormatNow)){
					calendar.set(Calendar.YEAR, anoTZ);
					calendar.set(Calendar.MONTH, mesTZ);
					calendar.set(Calendar.DAY_OF_MONTH, diaTZ);

				}

				if (horaBanco != horaTZ){
					calendar.set(Calendar.HOUR_OF_DAY, horaTZ);
					calendar.set(Calendar.MINUTE, minutosTZ);
				}

				calendar.add(Calendar.MINUTE,1);
				dataEnvio = new SimpleDateFormat(datePattern).format(calendar.getTime());
			}
		}
		catch (ParseException e){
			throw new RuntimeException(e);
		}
	}

	public void setDataEnvio(String dataEnvio) {
		this.dataEnvio = dataEnvio;
	}
}