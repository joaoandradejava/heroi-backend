package com.joaoandrade.sistemaheroi.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.joaoandrade.sistemaheroi.api.assembler.HeroiFullModelAssembler;
import com.joaoandrade.sistemaheroi.api.assembler.HeroiModelAssembler;
import com.joaoandrade.sistemaheroi.api.disassembler.HeroiInputDisassembler;
import com.joaoandrade.sistemaheroi.api.input.HeroiInput;
import com.joaoandrade.sistemaheroi.api.model.HeroiFullModel;
import com.joaoandrade.sistemaheroi.api.model.HeroiModel;
import com.joaoandrade.sistemaheroi.domain.model.Heroi;
import com.joaoandrade.sistemaheroi.domain.service.crud.CrudHeroiService;

@RestController
@RequestMapping("/herois")
public class HeroiController {

	@Autowired
	private CrudHeroiService crudHeroiService;

	@Autowired
	private HeroiModelAssembler heroiModelAssembler;

	@Autowired
	private HeroiFullModelAssembler heroiFullModelAssembler;

	@Autowired
	private HeroiInputDisassembler heroiInputDisassembler;

	@GetMapping
	public Page<HeroiModel> buscarTodos(Pageable pageable) {
		Page<Heroi> page = crudHeroiService.buscarTodos(pageable);

		return page.map(x -> heroiModelAssembler.toModel(x));
	}

	@GetMapping("/{id}")
	public HeroiFullModel buscarPorId(@PathVariable Long id) {
		Heroi heroi = crudHeroiService.buscarPorId(id);

		return heroiFullModelAssembler.toModel(heroi);
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public HeroiFullModel inserir(@Valid @RequestBody HeroiInput heroiInput) {
		Heroi heroi = crudHeroiService.inserir(heroiInputDisassembler.toDomainModel(heroiInput));

		return heroiFullModelAssembler.toModel(heroi);
	}

	@PutMapping("/{id}")
	public HeroiFullModel atualizar(@Valid @RequestBody HeroiInput heroiInput, @PathVariable Long id) {
		Heroi atual = crudHeroiService.buscarPorId(id);
		heroiInputDisassembler.copyToDomainModel(heroiInput, atual);
		atual = crudHeroiService.atualizar(atual);

		return heroiFullModelAssembler.toModel(atual);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletarPorId(@PathVariable Long id) {
		crudHeroiService.deletarPorId(id);
	}

}
