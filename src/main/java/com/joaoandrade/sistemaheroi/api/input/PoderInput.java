package com.joaoandrade.sistemaheroi.api.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PoderInput {

	@Size(min = 1, max = 255)
	@NotBlank
	private String nome;

	public PoderInput() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
