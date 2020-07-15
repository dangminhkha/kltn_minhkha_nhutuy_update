package com.example.demo.services;

import java.util.List;

import com.example.demo.dtos.TTDatTiecDTO;
import com.example.demo.entitys.ResultEntity;

public interface TTDatTiecService {
	
	public ResultEntity<TTDatTiecDTO> dattiec(TTDatTiecDTO dto);
	
	public ResultEntity<List<TTDatTiecDTO>> dsTheoIdKh(String id, String trangthai,int page ,int size);
	
	public ResultEntity<TTDatTiecDTO> timid(String id,String idnd,String loai);
	
	public ResultEntity<TTDatTiecDTO> doiTT(String id, String idnd, String trangthai,String loai);
	
	public int slPage(String id,String trangthai);
	
	public int slPageAdmin(String trangthai);
	
	public ResultEntity<List<TTDatTiecDTO>> dsTheoTT(String id,String trangthai,int page ,int size);
	
	public ResultEntity<TTDatTiecDTO> duyetTTDT(String userId, String id,int tiencoc);

	
}
