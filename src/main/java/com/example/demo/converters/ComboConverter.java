package com.example.demo.converters;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.dtos.CTComboDTO;
import com.example.demo.dtos.ComboDTO;
import com.example.demo.entitys.Combo;
import com.example.demo.utils.StringUtil;

public class ComboConverter {
	public static ComboDTO toDTO(Combo entity) {
		ComboDTO dto = new ComboDTO();
		dto.setId(entity.getId());
		dto.setGia(entity.getGia());
		dto.setTen(entity.getTen());
		List<CTComboDTO> cts = entity.getCtcombos()
				.stream()
				.filter(x->x.getTrangthai().equalsIgnoreCase(StringUtil.DANGHOATDONG))
				.map(CTComboConverter::toDTO)
				.collect(Collectors.toList());
		dto.setCtcombos(cts);
		
		Collections.sort(dto.getCtcombos(), new Comparator<CTComboDTO>() {

			@Override
			public int compare(CTComboDTO o1, CTComboDTO o2) {
				if(o1.getStt()<o2.getStt()) {
					return -1;
				}
				return 0;
			}
		});
		
		DecimalFormat dfgia = new DecimalFormat("#,###");
		dto.setGiaFM(dfgia.format(entity.getGia()));
		dto.setHinh(entity.getHinh());
		dto.setGhichu(entity.getGhichu());
		return dto;
	}
}
