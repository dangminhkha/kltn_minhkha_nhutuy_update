package com.example.demo.converters;

import java.text.DecimalFormat;

import com.example.demo.dtos.LuongDTO;
import com.example.demo.entitys.Luong;

public class LuongConverter {
	public static LuongDTO toDTO(Luong entity) {
		LuongDTO dto = new LuongDTO();
		dto.setId(entity.getId());
		dto.setNam(entity.getNam());
		dto.setNgaycongthang(entity.getNgaycongthang());
		dto.setNhanvien(NhanVienConverter.toDTO(entity.getNhanvien()));
		dto.setSongaycong(entity.getSongaycong());
		dto.setThang(entity.getThang());
		dto.setTienluong(entity.getTienluong());
		DecimalFormat df = new DecimalFormat("#,###");
		dto.setTienluongFM(df.format(entity.getTienluong()));
		return dto;
	}
}
