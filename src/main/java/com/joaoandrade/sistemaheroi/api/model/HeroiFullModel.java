package com.joaoandrade.sistemaheroi.api.model;

import java.util.ArrayList;
import java.util.List;

public class HeroiFullModel {
	private Long id;
	private String nome;
	private Integer idade;
	private String identidadeSecreta;
	private List<PoderModel> poderes = new ArrayList<>();

	public HeroiFullModel() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getIdentidadeSecreta() {
		return identidadeSecreta;
	}

	public void setIdentidadeSecreta(String identidadeSecreta) {
		this.identidadeSecreta = identidadeSecreta;
	}

	public List<PoderModel> getPoderes() {
		return poderes;
	}

	public void setPoderes(List<PoderModel> poderes) {
		this.poderes = poderes;
	}

}
