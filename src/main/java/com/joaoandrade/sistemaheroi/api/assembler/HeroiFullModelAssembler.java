package com.joaoandrade.sistemaheroi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joaoandrade.sistemaheroi.api.model.HeroiFullModel;
import com.joaoandrade.sistemaheroi.domain.model.Heroi;

@Component
public class HeroiFullModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public HeroiFullModel toModel(Heroi heroi) {
		return modelMapper.map(heroi, HeroiFullModel.class);
	}
}
