package com.example.demo.converters;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import com.example.demo.dtos.NguoiDungDTO;
import com.example.demo.dtos.NhanVienDTO;
import com.example.demo.entitys.NguoiDung;
import com.example.demo.entitys.NhanVien;

public class NhanVienConverter {
	public static NhanVienDTO toDTO(NhanVien entity) {
		SimpleDateFormat formatNS = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		DecimalFormat decimalFormat = new DecimalFormat("#,###");
		NhanVienDTO dto = new NhanVienDTO();
		dto.setId(entity.getId());
		dto.setTen(entity.getTen());
		dto.setNgaysinh(formatNS.format(entity.getNgaysinh()));
		dto.setSodienthoai(entity.getSodienthoai());
		dto.setEmail(entity.getEmail());
		dto.setGioitinh(entity.getGioitinh());
		dto.setNoisinh(entity.getNoisinh());
		dto.setSocmnd(entity.getSocmnd());
		dto.setLuong(entity.getLuong());
		dto.setNgaycapnhat(entity.getNgaycapnhat());
		dto.setNgaytao(entity.getNgaytao());
		dto.setTienluong(decimalFormat.format(entity.getLuong()));
		dto.setThoigiandangky(format.format(entity.getNgaytao()));
		dto.setThoigiancapnhat(format.format(entity.getNgaycapnhat()));
		dto.setChucvu(entity.getChucvu());
		return dto;
	}
}
