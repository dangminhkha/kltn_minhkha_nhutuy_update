package com.example.demo.entitys;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "luong")
public class Luong {
	@Id
	private String id;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
	@JoinColumn(name = "nhanvienid",referencedColumnName = "id")
	private NhanVien nhanvien;
	
	@Column(name = "thang")
	private int thang;
	@Column(name = "nam")
	private int nam;
	@Column(name = "ngaycongthang")
	private int ngaycongthang;
	@Column(name = "songaycong")
	private int songaycong;
	@Column(name = "tienluong")
	private double tienluong;
	public Luong() {
		super();
		// TODO Auto-generated constructor stub
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
	
	
	

}
