package com.example.demo.services;

import com.example.demo.dtos.HoaDonDTO;
import com.example.demo.entitys.ResultEntity;

public interface HoaDonService {
	public ResultEntity<HoaDonDTO> formatHoaDon(HoaDonDTO dto,String nvId);
	
	public ResultEntity<HoaDonDTO> thanhtoan(HoaDonDTO dto,String nvId);
	
	public ResultEntity<HoaDonDTO> timid(String id,String idnd,String loai);
}
