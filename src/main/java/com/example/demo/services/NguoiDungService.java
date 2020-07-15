package com.example.demo.services;

import java.util.List;

import com.example.demo.dtos.NguoiDungDTO;
import com.example.demo.dtos.NhanVienDTO;
import com.example.demo.entitys.ResultEntity;

public interface NguoiDungService {
	public ResultEntity<NguoiDungDTO> dangKy(NguoiDungDTO dto);
	
	public ResultEntity<NguoiDungDTO> timId(String id);
	
	public ResultEntity<NguoiDungDTO> luucapnhat(NguoiDungDTO dto,String id);
	
	public ResultEntity<NguoiDungDTO> doiMatKhau(String id,String mk, String newMK);
	
	public ResultEntity<List<NguoiDungDTO>> dsKH(String id,int page,int size);
	
	public ResultEntity<List<NguoiDungDTO>> tim(String keyword);
	
	public int slPage(String trangthai);
}
