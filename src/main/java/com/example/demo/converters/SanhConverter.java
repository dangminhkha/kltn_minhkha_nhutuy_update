package com.example.demo.converters;

import com.example.demo.dtos.SanhDTO;
import com.example.demo.entitys.Sanh;

public class SanhConverter {
	public static SanhDTO toDTO(Sanh entity) {
		SanhDTO dto = new SanhDTO();
		dto.setId(entity.getId());
		dto.setNgaycapnhat(entity.getNgaycapnhat());
		dto.setTen(entity.getTen());
		dto.setNhanvien(NhanVienConverter.toDTO(entity.getNhanvien()));
		dto.setNgaytao(entity.getNgaytao());
		dto.setSokhachtoida(entity.getSokhachtoida());
		dto.setSokhachtoithieu(entity.getSokhachtoithieu());
		dto.setMota(entity.getMota());
		dto.setHinh(entity.getHinh());
		return dto;
	}
}
