package com.example.demo.entitys;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "hoadon")
public class HoaDon {
	@Id
	private String id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "nhanvienid", referencedColumnName = "id")
	private NhanVien nhanvien;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "ttdattiecid", referencedColumnName = "id")
	private TTDatTiec ttDatTiec;

	@Column(name = "ngaythanhtoan")
	private Date ngaythanhtoan;
	@Column(name = "trangthai")
	private String trangthai;
	@Column(name = "gia")
	private double gia;
	
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="hoaDon")
	private Set<CTHoaDon> ctHoaDons;

	public HoaDon() {
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

	public TTDatTiec getTtDatTiec() {
		return ttDatTiec;
	}

	public void setTtDatTiec(TTDatTiec ttDatTiec) {
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

	public Set<CTHoaDon> getCtHoaDons() {
		return ctHoaDons;
	}

	public void setCtHoaDons(Set<CTHoaDon> ctHoaDons) {
		this.ctHoaDons = ctHoaDons;
	}
	
}
