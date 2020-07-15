package com.example.demo.entitys;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "binhluan")
public class BinhLuan {
	@Id
	private String id;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
	@JoinColumn(name = "nhanvienid",referencedColumnName = "id")
	private NhanVien nhanvien;
	
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
	@JoinColumn(name = "nguoidungid",referencedColumnName = "id")
	private NguoiDung nguoidung;
	

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "binhluan")
	private Set<TraLoiBinhLuan> traLoiBinhLuans;


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


	public Set<TraLoiBinhLuan> getTraLoiBinhLuans() {
		return traLoiBinhLuans;
	}


	public void setTraLoiBinhLuans(Set<TraLoiBinhLuan> traLoiBinhLuans) {
		this.traLoiBinhLuans = traLoiBinhLuans;
	}


	public BinhLuan() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	

}
