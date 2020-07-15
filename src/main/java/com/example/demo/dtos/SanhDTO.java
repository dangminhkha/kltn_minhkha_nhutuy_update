package com.example.demo.dtos;

import java.util.Date;


public class SanhDTO {

	private String id;

	private NhanVienDTO nhanvien;
	
	private String ten;
	private int sokhachtoithieu;
	private int sokhachtoida;
	private Date ngaytao;
	private Date ngaycapnhat;
	private String trangthai;
	private String hinh;
	private String mota;
	
	
	public String getMota() {
		return mota;
	}
	public void setMota(String mota) {
		this.mota = mota;
	}
	public String getHinh() {
		return hinh;
	}
	public void setHinh(String hinh) {
		this.hinh = hinh;
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
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public int getSokhachtoithieu() {
		return sokhachtoithieu;
	}
	public void setSokhachtoithieu(int sokhachtoithieu) {
		this.sokhachtoithieu = sokhachtoithieu;
	}
	public int getSokhachtoida() {
		return sokhachtoida;
	}
	public void setSokhachtoida(int sokhachtoida) {
		this.sokhachtoida = sokhachtoida;
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
	public String getTrangthai() {
		return trangthai;
	}
	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}
	public SanhDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "SanhDTO [id=" + id + ", nhanvien=" + nhanvien + ", ten=" + ten + ", sokhachtoithieu=" + sokhachtoithieu
				+ ", sokhachtoida=" + sokhachtoida + ", ngaytao=" + ngaytao + ", ngaycapnhat=" + ngaycapnhat
				+ ", trangthai=" + trangthai + ", hinh=" + hinh + ", mota=" + mota + "]";
	}
	
	
}
