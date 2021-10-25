package com.joaoandrade.sistemaheroi.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joaoandrade.sistemaheroi.api.input.HeroiInput;
import com.joaoandrade.sistemaheroi.domain.model.Heroi;

@Component
public class HeroiInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Heroi toDomainModel(HeroiInput heroiInput) {
		return modelMapper.map(heroiInput, Heroi.class);
	}

	public void copyToDomainModel(HeroiInput heroiInput, Heroi heroi) {
		modelMapper.map(heroiInput, heroi);
	}
}
