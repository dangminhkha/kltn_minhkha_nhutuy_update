package com.example.demo.dtos;


public class LuongDTO {
	private String id;
	private NhanVienDTO nhanvien;
	private int thang;
	private int nam;
	private int ngaycongthang;
	private int songaycong;
	private double tienluong;
	private String tienluongFM;
	
	public String getTienluongFM() {
		return tienluongFM;
	}
	public void setTienluongFM(String tienluongFM) {
		this.tienluongFM = tienluongFM;
	}
	public LuongDTO() {
		super();
		// TODO Auto-generated constructor stub
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
	public int getThang() {
		return thang;
	}
	public void setThang(int thang) {
		this.thang = thang;
	}
	public int getNam() {
		return nam;
	}
	public void setNam(int nam) {
		this.nam = nam;
	}
	public int getNgaycongthang() {
		return ngaycongthang;
	}
	public void setNgaycongthang(int ngaycongthang) {
		this.ngaycongthang = ngaycongthang;
	}
	public int getSongaycong() {
		return songaycong;
	}
	public void setSongaycong(int songaycong) {
		this.songaycong = songaycong;
	}
	public double getTienluong() {
		return tienluong;
	}
	public void setTienluong(double tienluong) {
		this.tienluong = tienluong;
	}
	@Override
	public String toString() {
		return "LuongDTO [id=" + id + ", nhanvien=" + nhanvien + ", thang=" + thang + ", nam=" + nam
				+ ", ngaycongthang=" + ngaycongthang + ", songaycong=" + songaycong + ", tienluong=" + tienluong + "]";
	}
	
	
}
