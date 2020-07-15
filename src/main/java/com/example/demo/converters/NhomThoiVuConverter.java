package com.example.demo.converters;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.dtos.NhanVienThoiVuDTO;
import com.example.demo.dtos.NhomThoiVuDTO;
import com.example.demo.entitys.NhomThoiVu;
import com.example.demo.utils.StringUtil;
import com.ibm.icu.text.SimpleDateFormat;

public class NhomThoiVuConverter {
	public static NhomThoiVuDTO toDTO(NhomThoiVu entity) {
		SimpleDateFormat datefm = new SimpleDateFormat("dd-MM-yyyy");
		NhomThoiVuDTO dto = new NhomThoiVuDTO();
		DecimalFormat df = new DecimalFormat("#,###");
		dto.setId(entity.getId());
		dto.setCalam(entity.getCalam());
		dto.setNgaylam(entity.getNgaylam());
		dto.setLuong(entity.getLuong());
		dto.setTen(entity.getTen());
		dto.setTrangthai(entity.getTrangthai());
		dto.setSlnv(entity.getNhanVienThoiVus().size());
		dto.setTongluong(dto.getSlnv()*dto.getLuong());
		dto.setTongLuongFM(df.format(dto.getTongluong()));
		dto.setLuongFM(df.format(entity.getLuong()));
		dto.setNgaylamFM(datefm.format(entity.getNgaylam()));
		if(entity.getNhanVienThoiVus()!=null && !entity.getNhanVienThoiVus().isEmpty()) {
			List<NhanVienThoiVuDTO> dtos = entity.getNhanVienThoiVus().stream()
					.filter(x->x.getTrangthai().equals(StringUtil.DANGHOATDONG))
					.map(NhanVienThoiVuConverter::toDTO).collect(Collectors.toList());
			dto.setNhanVienThoiVuDTOs(dtos);	
		}
		return dto;
	}

}
