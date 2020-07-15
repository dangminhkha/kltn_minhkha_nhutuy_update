package com.example.demo.services;

import java.util.Date;
import java.util.List;

import com.example.demo.dtos.NhanVienDTO;
import com.example.demo.entitys.ResultEntity;

public interface NhanVienService {
	public ResultEntity<List<NhanVienDTO>> dsNhanVien(String id,String chucvu,int page,int size);
	
	public ResultEntity<NhanVienDTO> themNV(NhanVienDTO dto, String id);
	
	public int slPage(String trangthai,String chucvu);
	
	public ResultEntity<NhanVienDTO> xoa(String adminId, String id);
	
	public ResultEntity<NhanVienDTO> doiMatKhau(String id,String mk, String newMK);
	
	public ResultEntity<List<NhanVienDTO>> tim(String keyword, String chucvu);
	
	public ResultEntity<List<NhanVienDTO>> dsAll(String trangthai,String nvId,int nam,int thang);
	
	public ResultEntity<List<NhanVienDTO>> dsCongTheoNgay(Date ngaycong,String trangthai, String loainhanvien);
	
	public ResultEntity<NhanVienDTO> nhanvienId (String id);
	
	public ResultEntity<List<NhanVienDTO>> dsNhanVienTheoNgay(Date ngaycong,String trangthai, String chucvu);
	
	public ResultEntity<NhanVienDTO> capNhat (NhanVienDTO dto);
}
