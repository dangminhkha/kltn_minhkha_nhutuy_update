package com.example.demo.converters;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import com.example.demo.dtos.LoaiMonDTO;
import com.example.demo.dtos.MonDTO;
import com.example.demo.entitys.Mon;
import com.example.demo.utils.StringUtil;

public class MonConverter {
	public static MonDTO toDTO(Mon entity) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		DecimalFormat dfm = new DecimalFormat("#,###");
		MonDTO dto = new MonDTO();
		dto.setId(entity.getId());
		dto.setTen(entity.getTen());
		dto.setGia(entity.getGia());
		dto.setHinh(entity.getHinh());
		dto.setNgaytao(dateFormat.format(entity.getNgaytao()));
		dto.setNhanvien(NhanVienConverter.toDTO(entity.getNhanvien()));
		dto.setGhichu(entity.getGhichu());
		dto.setNgaycapnhat(dateFormat.format(entity.getNgaycapnhat()));
		dto.setGiaFM(dfm.format(entity.getGia()));
		
		LoaiMonDTO loaidto = new LoaiMonDTO();
		loaidto.setId(entity.getLoaimon().getId());
		loaidto.setTen(entity.getLoaimon().getTen());
		if(entity.getLoaimon().getLoai().equalsIgnoreCase(StringUtil.DOAN)) {
			loaidto.setLoai("Món Ăn");
		}else {
			loaidto.setLoai("Nước Uống");
			
		}
		
		dto.setLoaimon(loaidto);
		return dto;
	}
}
