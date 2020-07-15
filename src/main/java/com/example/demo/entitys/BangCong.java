package com.example.demo.entitys;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bangcong")
public class BangCong {
	@Id
	private String id;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
	@JoinColumn(name = "nhanvienid",referencedColumnName = "id")
	private NhanVien nhanvien;
	
	@Column(name = "ngaycong")
	private Date ngaycong;
	@Column(name = "cong")
	private String cong;
	
	@Column(name = "luong")
	private double luong;
	
	
	public double getLuong() {
		return luong;
	}
	public void setLuong(double luong) {
		this.luong = luong;
	}
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
	public BangCong() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
