package com.joaoandrade.sistemaheroi.api.exceptionhandler;

public enum Error {
	SISTEMA_EXCEPTION("sistema-exception", "Erro do lado do client-side(Front-end)"),
	ENTIDADE_NAO_ENCONTRADA_EXCEPTION("entidade-nao-encontrada", "Entidade não encontrada"),
	ERRO_INTERNO_NO_SERVIDOR("erro-interno-no-servidor", "Erro interno no servidor"),
	ERRO_VALIDACAO_DOS_DADOS("erro-validacao-dos-dados", "Erro de validação dos dados"),
	ERRO_DESSERIALIZACAO_JSON("erro-desserializacao-json", "Erro na desserialização do JSON"),
	PROPRIEDADE_INEXISTENTE("propriedade-inexistente", "Propriedade inexistente");

	private String type;
	private String title;

	private Error(String type, String title) {
		this.type = "https://www.joaoandradejava.com.br/" + type;
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}

}
