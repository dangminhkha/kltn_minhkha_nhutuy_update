package com.example.demo.services;

import java.util.Date;
import java.util.List;

import com.example.demo.dtos.NhomThoiVuDTO;
import com.example.demo.entitys.ResultEntity;

public interface NhomThoiVuService {
	
	ResultEntity<NhomThoiVuDTO> luuNhom(String nvId,NhomThoiVuDTO dto);
	
	ResultEntity<List<NhomThoiVuDTO>> dsNhom(Date ngay);
	
	ResultEntity<NhomThoiVuDTO> timId(String id);
	

}
