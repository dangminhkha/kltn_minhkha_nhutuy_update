package com.example.demo.dtos;

import java.util.Date;
import java.util.List;

public class ComboDTO {
	private String id;
	private NhanVienDTO nhanvien;
	private NguoiDungDTO nguoidung;
	private String ten;
	private double gia;
	private Date ngaytao;
	private Date ngaycapnhat;
	private String trangthai;
	private List<CTComboDTO> ctcombos;
	private String giaFM;
	private String hinh;
	private String ghichu;
	
	
	
	public String getGhichu() {
		return ghichu;
	}
	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
	}
	public String getHinh() {
		return hinh;
	}
	public void setHinh(String hinh) {
		this.hinh = hinh;
	}
	public String getGiaFM() {
		return giaFM;
	}
	public void setGiaFM(String giaFM) {
		this.giaFM = giaFM;
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
	public NguoiDungDTO getNguoidung() {
		return nguoidung;
	}
	public void setNguoidung(NguoiDungDTO nguoidung) {
		this.nguoidung = nguoidung;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public double getGia() {
		return gia;
	}
	public void setGia(double gia) {
		this.gia = gia;
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
	public List<CTComboDTO> getCtcombos() {
		return ctcombos;
	}
	public void setCtcombos(List<CTComboDTO> ctcombos) {
		this.ctcombos = ctcombos;
	}
	public ComboDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ComboDTO [id=" + id + ", nhanvien=" + nhanvien + ", nguoidung=" + nguoidung + ", ten=" + ten + ", gia="
				+ gia + ", ngaytao=" + ngaytao + ", ngaycapnhat=" + ngaycapnhat + ", trangthai=" + trangthai
				+ ", ctcombos=" + ctcombos + "]";
	}
	
	
}
