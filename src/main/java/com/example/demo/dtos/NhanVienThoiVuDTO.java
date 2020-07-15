package com.example.demo.dtos;

import java.util.Date;

public class NhanVienThoiVuDTO {
	
	private String id;
	private String ten;
	private String cmnd;
	private String sodienthoai;
	private Date thoigianvao;
	private String trangthai;
	private String thoigianvaoFM;
	
	
	public String getThoigianvaoFM() {
		return thoigianvaoFM;
	}
	public void setThoigianvaoFM(String thoigianvaoFM) {
		this.thoigianvaoFM = thoigianvaoFM;
	}
	public NhanVienThoiVuDTO() {
		super();
		// TODO Auto-generated constructor stub
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
