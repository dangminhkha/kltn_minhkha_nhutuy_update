package com.example.demo.converters;

import com.example.demo.dtos.CTComboDTO;
import com.example.demo.entitys.CTCombo;

public class CTComboConverter {
	public static CTComboDTO toDTO(CTCombo entity) {
		CTComboDTO dto = new CTComboDTO();
		dto.setId(entity.getId());
		dto.setStt(entity.getStt());
		dto.setMon(MonConverter.toDTO(entity.getMon()));
		return dto;
	}
}
