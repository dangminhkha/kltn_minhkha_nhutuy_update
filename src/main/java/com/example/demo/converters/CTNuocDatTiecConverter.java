package com.example.demo.converters;

import com.example.demo.dtos.CTNuocDatTiecDTO;
import com.example.demo.entitys.CTNuocDatTiec;

public class CTNuocDatTiecConverter {
	public static CTNuocDatTiecDTO toDTO(CTNuocDatTiec entity) {
		CTNuocDatTiecDTO dto = new CTNuocDatTiecDTO();
		dto.setId(entity.getId());
		dto.setMon(MonConverter.toDTO(entity.getMon()));
		return dto;
	}

}
