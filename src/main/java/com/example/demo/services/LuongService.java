package com.example.demo.services;

import java.util.List;

import com.example.demo.dtos.LuongDTO;
import com.example.demo.entitys.ResultEntity;

public interface LuongService {
	ResultEntity<List<LuongDTO>> tinhLuong(List<LuongDTO> dstinhluong); 
	
	ResultEntity<List<LuongDTO>> dsTheoThang(String nvId, int thang, int nam); 
	
	ResultEntity<List<LuongDTO>> dsTheoNhanVien(String nvId, int nam); 

}
