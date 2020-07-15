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

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;

@Entity
@Table(name = "combo")
@Indexed
public class Combo {
	@Id
	private String id;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
	@JoinColumn(name = "nhanvienid",referencedColumnName = "id")
	private NhanVien nhanvien;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
	@JoinColumn(name = "nguoidungid",referencedColumnName = "id")
	private NguoiDung nguoidung;
	
	@Column(name = "ten")
	@Field
	private String ten;
	@Column(name = "gia")
	private double gia;
	@Column(name = "ngaytao")
	private Date ngaytao;
	@Column(name = "ngaycapnhat")
	private Date ngaycapnhat;
	@Column(name = "trangthai")
	private String trangthai;
	@Column(name = "hinh")
	private String hinh;
	@Column(name = "ghichu")
	@Field
	private String ghichu;
	
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="combo")
	private Set<CTCombo> ctcombos;
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="combo")
	private Set<TTDatTiec> ttDatTiecs;
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="combo")
	private Set<CTHoaDon> ctHoaDons;

	public Set<CTHoaDon> getCtHoaDons() {
		return ctHoaDons;
	}

	public void setCtHoaDons(Set<CTHoaDon> ctHoaDons) {
		this.ctHoaDons = ctHoaDons;
	}

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

	public Set<TTDatTiec> getTtDatTiecs() {
		return ttDatTiecs;
	}

	public void setTtDatTiecs(Set<TTDatTiec> ttDatTiecs) {
		this.ttDatTiecs = ttDatTiecs;
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

	public NguoiDung getNguoidung() {
		return nguoidung;
	}

	public void setNguoidung(NguoiDung nguoidung) {
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

	public Set<CTCombo> getCtcombos() {
		return ctcombos;
	}

	public void setCtcombos(Set<CTCombo> ctcombos) {
		this.ctcombos = ctcombos;
	}

	public Combo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
