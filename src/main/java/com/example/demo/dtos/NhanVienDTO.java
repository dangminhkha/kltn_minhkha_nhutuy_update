package com.example.demo.dtos;

import java.util.Date;

public class NhanVienDTO {
	private String id;
	private String ten;
	private String gioitinh;
	private String ngaysinh;
	private String noisinh;
	private String sodienthoai;
	private String email;
	private String chucvu;
	private String socmnd;
	private String trangthai;
	private String matkhau;
	private double luong;
	private Date ngaytao;
	private Date ngaycapnhat;
	private String thoigiandangky;
	private String thoigiancapnhat;
	private String rematkhau;
	private String tienluong;
	private boolean dachamcong;
	private int tongcongthang;
		
	public int getTongcongthang() {
		return tongcongthang;
	}
	public void setTongcongthang(int tongcongthang) {
		this.tongcongthang = tongcongthang;
	}
	public boolean isDachamcong() {
		return dachamcong;
	}
	public void setDachamcong(boolean dachamcong) {
		this.dachamcong = dachamcong;
	}
	public String getTienluong() {
		return tienluong;
	}
	public void setTienluong(String tienluong) {
		this.tienluong = tienluong;
	}
	public String getRematkhau() {
		return rematkhau;
	}
	public void setRematkhau(String rematkhau) {
		this.rematkhau = rematkhau;
	}
	public String getMatkhau() {
		return matkhau;
	}
	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}
	public String getThoigiandangky() {
		return thoigiandangky;
	}
	public void setThoigiandangky(String thoigiandangky) {
		this.thoigiandangky = thoigiandangky;
	}
	public String getThoigiancapnhat() {
		return thoigiancapnhat;
	}
	public void setThoigiancapnhat(String thoigiancapnhat) {
		this.thoigiancapnhat = thoigiancapnhat;
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
	public String getGioitinh() {
		return gioitinh;
	}
	public void setGioitinh(String gioitinh) {
		this.gioitinh = gioitinh;
	}
	public String getNgaysinh() {
		return ngaysinh;
	}
	public void setNgaysinh(String ngaysinh) {
		this.ngaysinh = ngaysinh;
	}
	public String getNoisinh() {
		return noisinh;
	}
	public void setNoisinh(String noisinh) {
		this.noisinh = noisinh;
	}
	public String getSodienthoai() {
		return sodienthoai;
	}
	public void setSodienthoai(String sodienthoai) {
		this.sodienthoai = sodienthoai;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getChucvu() {
		return chucvu;
	}
	public void setChucvu(String chucvu) {
		this.chucvu = chucvu;
	}
	public String getSocmnd() {
		return socmnd;
	}
	public void setSocmnd(String socmnd) {
		this.socmnd = socmnd;
	}
	public String getTrangthai() {
		return trangthai;
	}
	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}
	public double getLuong() {
		return luong;
	}
	public void setLuong(double luong) {
		this.luong = luong;
	}
	public Date getNgaytao() {
		return ngaytao;
	}
	public void setNgaytao(Date ngaytao) {
		this.ngaytao = ngaytao;
	}
	public Date getNgaycapnhat() {
		return ngaycapnhat;
	}
	public void setNgaycapnhat(Date ngaycapnhat) {
		this.ngaycapnhat = ngaycapnhat;
	}
	public NhanVienDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "NhanVienDTO [id=" + id + ", ten=" + ten + ", sodienthoai=" + sodienthoai + ", email=" + email
				+ ", chucvu=" + chucvu + ", dachamcong=" + dachamcong + "]";
	}
	
	
	
}
