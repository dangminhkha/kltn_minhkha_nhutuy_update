package com.example.demo.services;

import com.example.demo.dtos.NguoiDungDTO;
import com.example.demo.dtos.NhanVienDTO;
import com.example.demo.entitys.ResultEntity;

public interface DangNhapService {
	public ResultEntity<NguoiDungDTO> dangnhap(String username,String pass);
	
	public ResultEntity<NhanVienDTO> nvDN(String tk,String mk);
}
