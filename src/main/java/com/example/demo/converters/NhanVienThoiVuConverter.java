package com.example.demo.converters;

import com.example.demo.dtos.NhanVienThoiVuDTO;
import com.example.demo.entitys.NhanVienThoiVu;
import com.ibm.icu.text.SimpleDateFormat;

public class NhanVienThoiVuConverter {
	public static NhanVienThoiVuDTO toDTO(NhanVienThoiVu entity) {
		SimpleDateFormat datefm = new SimpleDateFormat("hh:mm:a dd-MM-yyyy");
		NhanVienThoiVuDTO dto = new NhanVienThoiVuDTO();
		dto.setCmnd(entity.getCmnd());
		dto.setId(entity.getId());
		dto.setSodienthoai(entity.getSodienthoai());
		dto.setTen(entity.getTen());
		dto.setThoigianvao(entity.getThoigianvao());
		dto.setTrangthai(entity.getTrangthai());		
		dto.setThoigianvaoFM(datefm.format(entity.getThoigianvao()));
		return dto;
	}
}
