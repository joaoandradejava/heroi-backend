package com.joaoandrade.sistemaheroi.domain.service.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaoandrade.sistemaheroi.domain.exception.HeroiNaoEncontradoException;
import com.joaoandrade.sistemaheroi.domain.model.Heroi;
import com.joaoandrade.sistemaheroi.domain.repository.HeroiRepository;

@Service
public class CrudHeroiService {

	@Autowired
	private HeroiRepository repository;

	public Page<Heroi> buscarTodos(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public Heroi buscarPorId(Long id) {
		return repository.findById(id).orElseThrow(() -> new HeroiNaoEncontradoException(id));
	}

	@Transactional
	public Heroi inserir(Heroi heroi) {
		return repository.save(heroi);
	}

	@Transactional
	public Heroi atualizar(Heroi heroi) {
		return repository.save(heroi);
	}

	@Transactional
	public void deletarPorId(Long id) {
		buscarPorId(id);

		repository.deleteById(id);
	}

}
