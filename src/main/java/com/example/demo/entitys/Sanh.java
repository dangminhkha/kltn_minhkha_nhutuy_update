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

@Entity
@Table(name = "sanh")
@Indexed
public class Sanh {
	@Id
	private String id;

	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
	@JoinColumn(name = "nhanvienid",referencedColumnName = "id")
	private NhanVien nhanvien;
	
	@Field
	@Column(name = "ten")
	private String ten;
	@Column(name = "sokhachtoithieu")
	private int sokhachtoithieu;
	@Column(name = "sokhachtoida")
	private int sokhachtoida;
	@Column(name = "ngaytao")
	private Date ngaytao;
	@Column(name = "ngaycapnhat")
	private Date ngaycapnhat;
	@Column(name = "trangthai")
	private String trangthai;
	@Column(name = "hinh")
	private String hinh;
	@Column(name = "mota")
	private String mota;
	
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="sanh")
	private Set<TTDatTiec> ttDatTiecs;
	
	
	
	public String getMota() {
		return mota;
	}
	public void setMota(String mota) {
		this.mota = mota;
	}
	public Set<TTDatTiec> getTtDatTiecs() {
		return ttDatTiecs;
	}
	public void setTtDatTiecs(Set<TTDatTiec> ttDatTiecs) {
		this.ttDatTiecs = ttDatTiecs;
	}
	public String getHinh() {
		return hinh;
	}
	public void setHinh(String hinh) {
		this.hinh = hinh;
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
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public int getSokhachtoithieu() {
		return sokhachtoithieu;
	}
	public void setSokhachtoithieu(int sokhachtoithieu) {
		this.sokhachtoithieu = sokhachtoithieu;
	}
	public int getSokhachtoida() {
		return sokhachtoida;
	}
	public void setSokhachtoida(int sokhachtoida) {
		this.sokhachtoida = sokhachtoida;
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
	public Sanh() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Sanh [id=" + id + ", nhanvien=" + nhanvien + ", ten=" + ten + ", sokhachtoithieu=" + sokhachtoithieu
				+ ", sokhachtoida=" + sokhachtoida + ", ngaytao=" + ngaytao + ", ngaycapnhat=" + ngaycapnhat
				+ ", trangthai=" + trangthai + ", hinh=" + hinh + ", mota=" + mota + ", ttDatTiecs=" + ttDatTiecs + "]";
	}
	
	
}
