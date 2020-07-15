package com.example.demo.entitys;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "nhomthoivu")
public class NhomThoiVu {
	@Id
	private String id;
	
	@Column(name = "ten")
	private String ten;
	@Column(name = "ngaylam")
	private Date ngaylam;
	@Column(name = "calam")
	private String calam;
	@Column(name = "luong")
	private double luong;
	@Column(name = "trangthai")
	private String trangthai;
	
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="nhomThoiVu")
	private Set<NhanVienThoiVu> nhanVienThoiVus;

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

	public Set<NhanVienThoiVu> getNhanVienThoiVus() {
		return nhanVienThoiVus;
	}

	public void setNhanVienThoiVus(Set<NhanVienThoiVu> nhanVienThoiVus) {
		this.nhanVienThoiVus = nhanVienThoiVus;
	}

	public NhomThoiVu() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
