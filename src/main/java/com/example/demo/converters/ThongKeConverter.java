package com.example.demo.converters;

import java.text.DecimalFormat;
import java.util.List;

import com.example.demo.dtos.ThongKeDTO;
import com.example.demo.entitys.BangCong;
import com.example.demo.entitys.HoaDon;
import com.example.demo.entitys.Luong;
import com.example.demo.entitys.NhomThoiVu;
import com.example.demo.entitys.TTDatTiec;
import com.example.demo.utils.StringUtil;

public class ThongKeConverter {
	public static ThongKeDTO toDTO(List<TTDatTiec> dsDatTiec,List<HoaDon> dsHoaDon, List<BangCong> dsBangCong,List<Luong> dsLuong) {
		ThongKeDTO dto = new ThongKeDTO();
		DecimalFormat df = new DecimalFormat("#,###");
		for (int i = 0; i < dsDatTiec.size(); i++) {
			if(dsDatTiec.get(i).getTrangthai().equals(StringUtil.DATHANHTOAN)) {
				dto.setTongdondathanhtoan(dto.getTongdondathanhtoan()+1);
			}else if(dsDatTiec.get(i).getTrangthai().equals(StringUtil.HUY)) {
				dto.setTongdondahuy(dto.getTongdondahuy()+1);
			}
			dto.setTongdondattiec(dto.getTongdondattiec()+1);
		}
		for (int i = 0; i < dsHoaDon.size(); i++) {
			dto.setTongtiendathanhtoan(dto.getTongtiendathanhtoan()+dsHoaDon.get(i).getGia());
		}
		for (int i = 0; i < dsBangCong.size(); i++) {
				dto.setTongluongnhanvienthoivu(dto.getTongluongnhanvienthoivu()+dsBangCong.get(i).getLuong());
		}
		for (int i = 0; i < dsLuong.size(); i++) {
			dto.setTongluongnhanvienchinhthuc(dto.getTongluongnhanvienchinhthuc()+dsLuong.get(i).getTienluong());
		}
		
		dto.setTongtiendathanhtoanFM(df.format(dto.getTongtiendathanhtoan()));
		dto.setTongluongnhanvienthoivuFM(df.format(dto.getTongluongnhanvienthoivu()));
		dto.setTongluongnhanvienchinhthucFM(df.format(dto.getTongluongnhanvienchinhthuc()));
		return dto;
	}

}
