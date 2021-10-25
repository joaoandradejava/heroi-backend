package com.joaoandrade.sistemaheroi.domain.exception;

public class HeroiNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public HeroiNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public HeroiNaoEncontradoException(long id) {
		super(String.format("O Heroi de id %d não foi encontrado no sistema!", id));
	}
}
