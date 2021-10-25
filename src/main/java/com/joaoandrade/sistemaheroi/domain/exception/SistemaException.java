package com.joaoandrade.sistemaheroi.domain.exception;

public class SistemaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SistemaException(String mensagem) {
		super(mensagem);
	}
}
