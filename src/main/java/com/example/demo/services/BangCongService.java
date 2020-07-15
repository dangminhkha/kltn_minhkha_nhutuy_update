package com.example.demo.services;

import java.util.Date;
import java.util.List;

import com.example.demo.dtos.BangCongDTO;
import com.example.demo.entitys.ResultEntity;

public interface BangCongService {
	ResultEntity<BangCongDTO> chamCong(BangCongDTO dto);
	
	ResultEntity<List<BangCongDTO>> dsCongNhanVien(String nvId,int thang, int nam);
	
	ResultEntity<List<BangCongDTO>> dsCongTheoNgay(Date ngaycong,String trangthai, String chucvu);

}
