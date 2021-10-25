package com.joaoandrade.sistemaheroi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaoandrade.sistemaheroi.domain.exception.PoderNaoEncontradoException;
import com.joaoandrade.sistemaheroi.domain.exception.SistemaException;
import com.joaoandrade.sistemaheroi.domain.model.Poder;
import com.joaoandrade.sistemaheroi.domain.repository.PoderRepository;
import com.joaoandrade.sistemaheroi.domain.service.crud.CrudHeroiService;

@Service
public class HeroiPoderService {

	@Autowired
	private PoderRepository poderRepository;

	@Autowired
	private CrudHeroiService crudHeroiService;

	public Poder buscarPoderDoheroi(Long heroiId, Long poderId) {
		crudHeroiService.buscarPorId(heroiId);
		poderRepository.findById(poderId).orElseThrow(() -> new PoderNaoEncontradoException(poderId));

		return poderRepository.buscarPoderDoheroi(heroiId, poderId).orElseThrow(() -> new SistemaException(
				String.format("O Poder de id %d não estar associado com o Heroi de id %d", poderId, heroiId)));
	}

	@Transactional
	public Poder inserir(Poder poder, Long heroiId) {
		poder.setHeroi(crudHeroiService.buscarPorId(heroiId));

		return poderRepository.save(poder);
	}

	@Transactional
	public Poder atualizar(Poder poder) {
		return poderRepository.save(poder);
	}

	@Transactional
	public void deletar(Long heroiId, Long poderId) {
		buscarPoderDoheroi(heroiId, poderId);

		poderRepository.deleteById(poderId);
	}

}
