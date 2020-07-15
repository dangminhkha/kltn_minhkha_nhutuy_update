package com.example.demo.services;

import java.util.List;

import com.example.demo.dtos.ComboDTO;
import com.example.demo.entitys.ResultEntity;

public interface ComboService {
	public ResultEntity<ComboDTO> themCombo(String id,ComboDTO dto);
	
	public ResultEntity<List<ComboDTO>> dsComBo(int page,int size);
	
	public ResultEntity<List<ComboDTO>> dsChon();
	
	public ResultEntity<ComboDTO> timID(String id);
	
	public int slPage(String trangthai);
	
	public ResultEntity<ComboDTO> xoa(String id,String nvId);
	
	public ResultEntity<List<ComboDTO>> tim(String keyword);
}
