package com.example.demo.services;

import java.util.List;

import com.example.demo.dtos.MonDTO;
import com.example.demo.entitys.ResultEntity;

public interface MonService {
	public ResultEntity<MonDTO> themMon(String id, MonDTO dto);
	
	public ResultEntity<List<MonDTO>> dsMonTheoLoai(String idloai,int page,int size);
	
	public ResultEntity<List<MonDTO>> dsAll(String loai);
	
	public ResultEntity<MonDTO> timMon(String id);
	
	public int slPage(String loai,String trangthai);
	
	public ResultEntity<MonDTO> xoa(String id,String nvId);
	
	public ResultEntity<List<MonDTO>> tim(String keyword);


}
