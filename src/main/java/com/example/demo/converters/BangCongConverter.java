package com.example.demo.converters;

import java.text.DecimalFormat;

import com.example.demo.dtos.BangCongDTO;
import com.example.demo.entitys.BangCong;
import com.example.demo.utils.StringUtil;
import com.ibm.icu.text.SimpleDateFormat;

public class BangCongConverter {
	public static BangCongDTO toDTO(BangCong entity) {
		SimpleDateFormat datefm = new SimpleDateFormat("dd-MM-yyyy");
		DecimalFormat df = new DecimalFormat("#,###");
		BangCongDTO dto = new BangCongDTO();
		dto.setId(entity.getId());
		dto.setNgaycong(entity.getNgaycong());
		dto.setNhanvien(NhanVienConverter.toDTO(entity.getNhanvien()));
		dto.setCong(entity.getCong());
		dto.setNgaycongFM(datefm.format(dto.getNgaycong()));
		if (dto.getNhanvien().getChucvu().equals(StringUtil.NHANVIENTHOIVU)) {
			dto.setLuong(entity.getLuong());
			dto.setLuongFM(df.format(entity.getLuong()));
		}
		return dto;
	}

}
