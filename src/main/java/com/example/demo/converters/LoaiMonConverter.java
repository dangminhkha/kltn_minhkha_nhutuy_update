package com.example.demo.converters;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.dtos.LoaiMonDTO;
import com.example.demo.dtos.MonDTO;
import com.example.demo.entitys.LoaiMon;
import com.example.demo.utils.StringUtil;

public class LoaiMonConverter {
	public static LoaiMonDTO toDTO(LoaiMon entity) {
		LoaiMonDTO dto = new LoaiMonDTO();
		dto.setId(entity.getId());
		dto.setTen(entity.getTen());
		if(entity.getLoai().equalsIgnoreCase(StringUtil.DOAN)) {
			dto.setLoai("Món Ăn");
		}else {
			dto.setLoai("Nước Uống");
		}
		if(entity.getMons()!=null) {
			List<MonDTO> dsMon = entity.getMons().stream().filter(x->x.getTrangthai().equalsIgnoreCase(StringUtil.DANGHOATDONG))
					.map(MonConverter::toDTO).collect(Collectors.toList());
			dto.setMons(dsMon);
		}
		return dto;
	}
}
