package com.example.demo.converters;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.dtos.CTHoaDonDTO;
import com.example.demo.dtos.HoaDonDTO;
import com.example.demo.entitys.HoaDon;
import com.ibm.icu.text.SimpleDateFormat;

public class HoaDonConverter {
	public static HoaDonDTO toDTO(HoaDon entity) {
		DecimalFormat dfgia = new DecimalFormat("#,###");
		SimpleDateFormat datefm = new SimpleDateFormat("hh:mm:a dd-MM-yyyy");
		HoaDonDTO dto = new HoaDonDTO();
		dto.setId(entity.getId());
		List<CTHoaDonDTO> dsDTO = entity.getCtHoaDons().stream().map(CTHoaDonConverter::toDTO).collect(Collectors.toList());
		dto.setCtHoaDons(dsDTO);
		dto.setNgaythanhtoan(entity.getNgaythanhtoan());
		if (entity.getNhanvien()!=null) {
			dto.setNhanvien(NhanVienConverter.toDTO(entity.getNhanvien()));
		}
		dto.setTrangthai(entity.getTrangthai());
		dto.setTtDatTiec(TTDatTiecConverter.toDTO(entity.getTtDatTiec()));
		dto.setGia(entity.getGia());
		dto.setGiaFM(dfgia.format(entity.getGia()));
		dto.setTientra(dfgia.format(dto.getGia()-entity.getTtDatTiec().getTiencoc()));
		dto.setNgaythanhtoanFM(datefm.format(dto.getNgaythanhtoan()));
		return dto;
	}
}
