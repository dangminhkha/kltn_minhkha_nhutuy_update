package com.example.demo.services;

import java.util.List;

import com.example.demo.dtos.LoaiMonDTO;
import com.example.demo.entitys.ResultEntity;

public interface LoaiMonService {
	public ResultEntity<LoaiMonDTO> themLoai(String id, LoaiMonDTO dto);
	
	public ResultEntity<List<LoaiMonDTO>> loaiMonAll();
	
	public ResultEntity<List<LoaiMonDTO>> pageLoaiMon(String nvId,int page, int size);
	
	public int slPage(String trangthai);
	
	public ResultEntity<LoaiMonDTO> xoa(String id, String nvId);
	
	public ResultEntity<List<LoaiMonDTO>> loaiMonAns(String trangthai,String loai);

}
