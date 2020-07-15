package com.example.demo.dtos;


public class MonDTO {
	private String id;
	private NhanVienDTO nhanvien;
	private String ten;
	private double gia;
	private String ngaytao;
	private String ngaycapnhat;
	private String trangthai;
	private String hinh;
	private String ghichu;
	private String loai;
	private LoaiMonDTO loaimon;
	private String giaFM;
	
	
	public LoaiMonDTO getLoaimon() {
		return loaimon;
	}
	public void setLoaimon(LoaiMonDTO loaimon) {
		this.loaimon = loaimon;
	}
	public String getGiaFM() {
		return giaFM;
	}
	public void setGiaFM(String giaFM) {
		this.giaFM = giaFM;
	}
	public String getLoai() {
		return loai;
	}
	public void setLoai(String loai) {
		this.loai = loai;
	}
	public String getGhichu() {
		return ghichu;
	}
	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
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
	public double getGia() {
		return gia;
	}
	public void setGia(double gia) {
		this.gia = gia;
	}
	public String getNgaytao() {
		return ngaytao;
	}
	public void setNgaytao(String ngaytao) {
		this.ngaytao = ngaytao;
	}
	public String getNgaycapnhat() {
		return ngaycapnhat;
	}
	public void setNgaycapnhat(String ngaycapnhat) {
		this.ngaycapnhat = ngaycapnhat;
	}
	public String getTrangthai() {
		return trangthai;
	}
	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}
	public String getHinh() {
		return hinh;
	}
	public void setHinh(String hinh) {
		this.hinh = hinh;
	}
	public MonDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "MonDTO [id=" + id + ", nhanvien=" + nhanvien + ", ten=" + ten + ", gia=" + gia + ", ngaytao=" + ngaytao
				+ ", ngaycapnhat=" + ngaycapnhat + ", trangthai=" + trangthai + ", hinh=" + hinh + ", ghichu=" + ghichu
				+ ", loai=" + loai + "]";
	}
	
}
