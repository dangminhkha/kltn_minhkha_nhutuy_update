package com.example.demo.converters;

import java.text.DecimalFormat;

import com.example.demo.dtos.CTHoaDonDTO;
import com.example.demo.entitys.CTHoaDon;

public class CTHoaDonConverter {
	public static CTHoaDonDTO toDTO(CTHoaDon entity) {
		DecimalFormat dfgia = new DecimalFormat("#,###");
		CTHoaDonDTO dto = new CTHoaDonDTO();
		dto.setId(entity.getId());
		dto.setSoluong(entity.getSoluong());
		dto.setGia(entity.getGia());
		if(entity.getCombo()!=null) {
			dto.setCombo(ComboConverter.toDTO(entity.getCombo()));
		}
		if(entity.getMon()!=null) {
			dto.setMon(MonConverter.toDTO(entity.getMon()));
		}
		if(entity.getTen()!=null) {
			dto.setTen(entity.getTen());
			dto.setGiaDon(dfgia.format(entity.getGia()/entity.getSoluong()));
		}
		dto.setGiaFM(dfgia.format(entity.getGia()));
		return dto;
	}

}
