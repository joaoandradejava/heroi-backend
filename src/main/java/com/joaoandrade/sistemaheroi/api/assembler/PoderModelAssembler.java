package com.joaoandrade.sistemaheroi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joaoandrade.sistemaheroi.domain.model.Poder;

import com.joaoandrade.sistemaheroi.api.model.PoderModel;

@Component
public class PoderModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public PoderModel toModel(Poder poder) {
		return modelMapper.map(poder, PoderModel.class);
	}
}
