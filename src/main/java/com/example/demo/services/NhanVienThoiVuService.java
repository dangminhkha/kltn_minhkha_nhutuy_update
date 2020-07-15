package com.example.demo.services;

import com.example.demo.dtos.NhanVienThoiVuDTO;
import com.example.demo.entitys.ResultEntity;

public interface NhanVienThoiVuService {
	ResultEntity<NhanVienThoiVuDTO> themNvtv(String nhomId,NhanVienThoiVuDTO dto);
}
