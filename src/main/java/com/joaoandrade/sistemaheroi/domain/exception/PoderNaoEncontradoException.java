package com.joaoandrade.sistemaheroi.domain.exception;

public class PoderNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public PoderNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public PoderNaoEncontradoException(long id) {
		super(String.format("O Poder de id %d não foi encontrado no sistema!", id));
	}
}
