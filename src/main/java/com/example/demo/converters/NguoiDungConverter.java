package com.example.demo.converters;

import java.text.SimpleDateFormat;

import com.example.demo.dtos.NguoiDungDTO;
import com.example.demo.entitys.NguoiDung;
import com.example.demo.utils.StringUtil;

public class NguoiDungConverter {
	public static NguoiDungDTO toDTO(NguoiDung entity) {
		SimpleDateFormat formatNS = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		NguoiDungDTO dto = new NguoiDungDTO();
		dto.setId(entity.getId());
		dto.setTen(entity.getTen());
		dto.setNgaysinh(formatNS.format(entity.getNgaysinh()));
		dto.setSodienthoai(entity.getSodienthoai());
		dto.setEmail(entity.getEmail());
		dto.setGioitinh(entity.getGioitinh());
		dto.setNoisinh(entity.getNoisinh());
		dto.setSocmnd(entity.getSocmnd());
		dto.setSodu(entity.getSodu());
		dto.setSolandat(entity.getSolandat());
		dto.setNgaycapnhat(entity.getNgaycapnhat());
		dto.setNgaydangky(entity.getNgaydangky());
		dto.setThoigiandangky(format.format(entity.getNgaydangky()));
		dto.setThoigiancapnhat(format.format(entity.getNgaycapnhat()));
		return dto;
	}
}
