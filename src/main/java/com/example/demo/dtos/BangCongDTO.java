package com.example.demo.dtos;

import java.util.Date;

public class BangCongDTO {
	private String id;
	private NhanVienDTO nhanvien;
	private Date ngaycong;
	private String cong;
	private String ngaycongFM;
	private double luong;
	private String luongFM;
	
	
	public double getLuong() {
		return luong;
	}
	public void setLuong(double luong) {
		this.luong = luong;
	}
	public String getLuongFM() {
		return luongFM;
	}
	public void setLuongFM(String luongFM) {
		this.luongFM = luongFM;
	}
	public String getNgaycongFM() {
		return ngaycongFM;
	}
	public void setNgaycongFM(String ngaycongFM) {
		this.ngaycongFM = ngaycongFM;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public NhanVienDTO getNhanvien() {
		return nhanvien;
	}
	public void setNhanvien(NhanVienDTO nhanvien) {
		this.nhanvien = nhanvien;
	}
	public Date getNgaycong() {
		return ngaycong;
	}
	public void setNgaycong(Date ngaycong) {
		this.ngaycong = ngaycong;
	}
	public String getCong() {
		return cong;
	}
	public void setCong(String cong) {
		this.cong = cong;
	}
	public BangCongDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
