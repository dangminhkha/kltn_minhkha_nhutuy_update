package com.example.demo.dtos;

import java.util.Date;
import java.util.List;

public class NhomThoiVuDTO {

	private String id;
	private String ten;
	private Date ngaylam;
	private String calam;
	private double luong;
	private String trangthai;
	private int slnv;
	private double tongluong;
	private String tongLuongFM;
	private String luongFM;
	private String ngaylamFM;
	private List<NhanVienThoiVuDTO> nhanVienThoiVuDTOs;
	
	
	public String getNgaylamFM() {
		return ngaylamFM;
	}
	public void setNgaylamFM(String ngaylamFM) {
		this.ngaylamFM = ngaylamFM;
	}
	public int getSlnv() {
		return slnv;
	}
	public void setSlnv(int slnv) {
		this.slnv = slnv;
	}
	
	
	public double getTongluong() {
		return tongluong;
	}
	public void setTongluong(double tongluong) {
		this.tongluong = tongluong;
	}
	public String getTongLuongFM() {
		return tongLuongFM;
	}
	public void setTongLuongFM(String tongLuongFM) {
		this.tongLuongFM = tongLuongFM;
	}
	public String getLuongFM() {
		return luongFM;
	}
	public void setLuongFM(String luongFM) {
		this.luongFM = luongFM;
	}
	public List<NhanVienThoiVuDTO> getNhanVienThoiVuDTOs() {
		return nhanVienThoiVuDTOs;
	}
	public void setNhanVienThoiVuDTOs(List<NhanVienThoiVuDTO> nhanVienThoiVuDTOs) {
		this.nhanVienThoiVuDTOs = nhanVienThoiVuDTOs;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public Date getNgaylam() {
		return ngaylam;
	}
	public void setNgaylam(Date ngaylam) {
		this.ngaylam = ngaylam;
	}
	public String getCalam() {
		return calam;
	}
	public void setCalam(String calam) {
		this.calam = calam;
	}
	public double getLuong() {
		return luong;
	}
	public void setLuong(double luong) {
		this.luong = luong;
	}
	
	public String getTrangthai() {
		return trangthai;
	}
	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}
	public NhomThoiVuDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
