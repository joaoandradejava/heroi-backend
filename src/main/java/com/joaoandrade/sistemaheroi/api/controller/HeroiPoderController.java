package com.joaoandrade.sistemaheroi.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.joaoandrade.sistemaheroi.api.assembler.PoderModelAssembler;
import com.joaoandrade.sistemaheroi.api.disassembler.PoderInputDisassembler;
import com.joaoandrade.sistemaheroi.api.input.PoderInput;
import com.joaoandrade.sistemaheroi.api.model.PoderModel;
import com.joaoandrade.sistemaheroi.domain.model.Poder;
import com.joaoandrade.sistemaheroi.domain.service.HeroiPoderService;

@RestController
@RequestMapping("/herois/{heroiId}/poderes")
public class HeroiPoderController {

	@Autowired
	private HeroiPoderService heroiPoderService;

	@Autowired
	private PoderInputDisassembler poderInputDisassembler;

	@Autowired
	private PoderModelAssembler poderModelAssembler;

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public PoderModel inserir(@Valid @RequestBody PoderInput poderInput, @PathVariable Long heroiId) {
		Poder poder = heroiPoderService.inserir(poderInputDisassembler.toDomainModel(poderInput), heroiId);

		return poderModelAssembler.toModel(poder);
	}

	@PutMapping("/{poderId}")
	public PoderModel atualizar(@Valid @RequestBody PoderInput poderInput, @PathVariable Long heroiId,
			@PathVariable Long poderId) {
		Poder poder = heroiPoderService.buscarPoderDoheroi(heroiId, poderId);
		poderInputDisassembler.copyToDomainModel(poderInput, poder);
		poder = heroiPoderService.atualizar(poder);

		return poderModelAssembler.toModel(poder);
	}

	@DeleteMapping("/{poderId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long heroiId, @PathVariable Long poderId) {
		heroiPoderService.deletar(heroiId, poderId);
	}

}
