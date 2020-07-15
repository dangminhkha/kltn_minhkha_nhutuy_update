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
@Table(name = "nhanvienthoivu")
public class NhanVienThoiVu {
	@Id
	private String id;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
	@JoinColumn(name = "nhomthoivuid",referencedColumnName = "id")
	private NhomThoiVu nhomThoiVu;
	
	@Column(name = "ten")
	private String ten;
	@Column(name = "cmnd")
	private String cmnd;
	@Column(name = "sodienthoai")
	private String sodienthoai;
	@Column(name = "thoigianvao")
	private Date thoigianvao;
	@Column(name = "trangthai")
	private String trangthai;
	public NhanVienThoiVu() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public NhomThoiVu getNhomThoiVu() {
		return nhomThoiVu;
	}
	public void setNhomThoiVu(NhomThoiVu nhomThoiVu) {
		this.nhomThoiVu = nhomThoiVu;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public String getCmnd() {
		return cmnd;
	}
	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}
	public String getSodienthoai() {
		return sodienthoai;
	}
	public void setSodienthoai(String sodienthoai) {
		this.sodienthoai = sodienthoai;
	}
	public Date getThoigianvao() {
		return thoigianvao;
	}
	public void setThoigianvao(Date thoigianvao) {
		this.thoigianvao = thoigianvao;
	}
	public String getTrangthai() {
		return trangthai;
	}
	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}

	
}
