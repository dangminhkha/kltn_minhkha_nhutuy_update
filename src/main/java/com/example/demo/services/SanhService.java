package com.example.demo.services;

import java.util.List;

import com.example.demo.dtos.SanhDTO;
import com.example.demo.dtos.TTDatTiecDTO;
import com.example.demo.entitys.ResultEntity;

public interface SanhService {
	public ResultEntity<SanhDTO> themSanh(String id, SanhDTO dto);
	
	public ResultEntity<List<SanhDTO>> dsSanh(int page,int size);
	
	public ResultEntity<List<SanhDTO>> dsSanhCho(TTDatTiecDTO dto);
	
	public ResultEntity<SanhDTO> timId(String id);
	
	public int slPage(String trangthai);
	
	public ResultEntity<SanhDTO> xoa(String id,String nvId); 
	
	public ResultEntity<List<SanhDTO>> timKeyword(String keyword);

}
