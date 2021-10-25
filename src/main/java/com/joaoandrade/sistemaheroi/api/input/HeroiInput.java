package com.joaoandrade.sistemaheroi.api.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

public class HeroiInput {

	@Size(min = 3, max = 60)
	@NotBlank
	private String nome;

	@NotNull
	@Range(min = 1, max = 10)
	private Integer idade;

	public HeroiInput() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

}
