package com.example.demo.entitys;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "loaimon")
public class LoaiMon {
	@Id
	private String id;
	
	@Column(name = "ten")
	private String ten;
	
	@Column(name = "trangthai")
	private String trangthai;
	@Column(name = "loai")
	private String loai;
	
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="loaimon")
	private Set<Mon> mons;

	public LoaiMon() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getLoai() {
		return loai;
	}

	public void setLoai(String loai) {
		this.loai = loai;
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

	public String getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}

	public Set<Mon> getMons() {
		return mons;
	}

	public void setMons(Set<Mon> mons) {
		this.mons = mons;
	}
	
	
}
