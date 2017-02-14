package br.com.m2msolutions.workerbilhetagem.commom;

public enum CodigoErroEnum {
	Cod00("00", "Nenhum registro encontrado"), Cod01("01", "Erro ao consultar Web Service."), Cod03("03",
			"Pesquisa com data invalida ou fora do periodo permitido."), Cod404("404",
					"Recurso nao encontrado"), Cod401("401", "Acesso nao Autorizado");

	private String codigo;
	private String descricao;

	private CodigoErroEnum(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return codigo + ": " + descricao;
	}

}
