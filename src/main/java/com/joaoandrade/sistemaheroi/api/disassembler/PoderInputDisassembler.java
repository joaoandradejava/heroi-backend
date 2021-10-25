package com.joaoandrade.sistemaheroi.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joaoandrade.sistemaheroi.api.input.PoderInput;
import com.joaoandrade.sistemaheroi.domain.model.Poder;

@Component
public class PoderInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Poder toDomainModel(PoderInput poderInput) {
		return modelMapper.map(poderInput, Poder.class);
	}

	public void copyToDomainModel(PoderInput poderInput, Poder poder) {
		modelMapper.map(poderInput, poder);
	}
}
