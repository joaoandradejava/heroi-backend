package com.joaoandrade.sistemaheroi.api.assembler;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.joaoandrade.sistemaheroi.api.model.HeroiModel;
import com.joaoandrade.sistemaheroi.domain.model.Heroi;

@Component
public class HeroiModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public HeroiModel toModel(Heroi heroi) {
		return modelMapper.map(heroi, HeroiModel.class);
	}

}
