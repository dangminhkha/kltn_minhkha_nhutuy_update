package com.example.demo.dtos;

import java.util.Date;
import java.util.List;

public class HoaDonDTO {
	private String id;
	private NhanVienDTO nhanvien;
	private TTDatTiecDTO ttDatTiec;
	private Date ngaythanhtoan;
	private String trangthai;
	private double gia;
	private String giaFM;
	private String tientra;
	private String ngaythanhtoanFM;
	
	
	
	public String getNgaythanhtoanFM() {
		return ngaythanhtoanFM;
	}

	public void setNgaythanhtoanFM(String ngaythanhtoanFM) {
		this.ngaythanhtoanFM = ngaythanhtoanFM;
	}

	public String getTientra() {
		return tientra;
	}

	public void setTientra(String tientra) {
		this.tientra = tientra;
	}
	private List<CTHoaDonDTO> ctHoaDons;
	public HoaDonDTO() {
		super();
		// TODO Auto-generated constructor stub
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
	public TTDatTiecDTO getTtDatTiec() {
		return ttDatTiec;
	}
	public void setTtDatTiec(TTDatTiecDTO ttDatTiec) {
		this.ttDatTiec = ttDatTiec;
	}
	public Date getNgaythanhtoan() {
		return ngaythanhtoan;
	}
	public void setNgaythanhtoan(Date ngaythanhtoan) {
		this.ngaythanhtoan = ngaythanhtoan;
	}
	public String getTrangthai() {
		return trangthai;
	}
	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}
	public double getGia() {
		return gia;
	}
	public void setGia(double gia) {
		this.gia = gia;
	}
	public List<CTHoaDonDTO> getCtHoaDons() {
		return ctHoaDons;
	}
	public void setCtHoaDons(List<CTHoaDonDTO> ctHoaDons) {
		this.ctHoaDons = ctHoaDons;
	}
	@Override
	public String toString() {
		return "HoaDonDTO [id=" + id + ", nhanvien=" + nhanvien + ", ttDatTiec=" + ttDatTiec + ", ngaythanhtoan="
				+ ngaythanhtoan + ", trangthai=" + trangthai + ", gia=" + gia + ", ctHoaDons=" + ctHoaDons + "]";
	}
	
}
