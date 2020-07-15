package com.example.demo.entitys;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "binhluantraloi")
public class TraLoiBinhLuan {
	@Id
	private String id;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
	@JoinColumn(name = "nhanvienid",referencedColumnName = "id")
	private NhanVien nhanvien;
	
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
	@JoinColumn(name = "nguoidungid",referencedColumnName = "id")
	private NguoiDung nguoidung;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
	@JoinColumn(name = "binhluanid",referencedColumnName = "id")
	private BinhLuan binhluan;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public NhanVien getNhanvien() {
		return nhanvien;
	}

	public void setNhanvien(NhanVien nhanvien) {
		this.nhanvien = nhanvien;
	}

	public NguoiDung getNguoidung() {
		return nguoidung;
	}

	public void setNguoidung(NguoiDung nguoidung) {
		this.nguoidung = nguoidung;
	}

	public BinhLuan getBinhluan() {
		return binhluan;
	}

	public void setBinhluan(BinhLuan binhluan) {
		this.binhluan = binhluan;
	}

	public TraLoiBinhLuan() {
		super();
		// TODO Auto-generated constructor stub
	}

}
