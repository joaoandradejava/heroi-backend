package com.joaoandrade.sistemaheroi.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

@Entity
public class Heroi {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Integer idade;
	private String identidadeSecreta;

	@OneToMany(mappedBy = "heroi", orphanRemoval = true)
	private List<Poder> poderes = new ArrayList<>();

	public Heroi() {
	}

	public Heroi(Long id, String nome, Integer idade, String identidadeSecreta) {
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.identidadeSecreta = identidadeSecreta;
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

	public List<Poder> getPoderes() {
		return poderes;
	}

	public void setPoderes(List<Poder> poderes) {
		this.poderes = poderes;
	}

	@PrePersist
	public void inserirIndetidadeSecreta() {
		this.identidadeSecreta = UUID.randomUUID().toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Heroi other = (Heroi) obj;
		return Objects.equals(id, other.id);
	}

}
