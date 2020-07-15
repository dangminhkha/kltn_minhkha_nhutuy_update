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

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@Table(name = "nguoidung")
@Indexed
public class NguoiDung {
	@Id
	private String id;
	@Column(name = "ten")
	@Field
	private String ten;
	@Column(name = "gioitinh")
	private String gioitinh;
	@Column(name = "ngaysinh")
	private Date ngaysinh;
	@Column(name = "noisinh")
	private String noisinh;
	@Column(name = "sodienthoai")
	@Field
	private String sodienthoai;
	@Column(name = "email")
	@Field
	private String email;
	@Column(name = "ngaydangky")
	private Date ngaydangky;
	@Column(name = "ngaycapnhat")
	private Date ngaycapnhat;
	@Column(name = "trangthai")
	private String trangthai;
	@Column(name = "socmnd")
	private String socmnd;
	@Column(name = "loai")
	private String loai;
	@Column(name = "matkhau")
	private String matkhau;
	@Column(name = "solandat")
	private int solandat;
	@Column(name = "sodu")
	private double sodu;
	
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="nguoidung")
	private Set<Combo> combos;
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="nguoidung")
	private Set<TTDatTiec> ttDatTiecs;
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="nguoidung")
	private Set<BinhLuan> binhLuans;
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="nguoidung")
	private Set<TraLoiBinhLuan> traLoiBinhLuans;
	
	
	
	
	public Set<BinhLuan> getBinhLuans() {
		return binhLuans;
	}
	public void setBinhLuans(Set<BinhLuan> binhLuans) {
		this.binhLuans = binhLuans;
	}
	public Set<TraLoiBinhLuan> getTraLoiBinhLuans() {
		return traLoiBinhLuans;
	}
	public void setTraLoiBinhLuans(Set<TraLoiBinhLuan> traLoiBinhLuans) {
		this.traLoiBinhLuans = traLoiBinhLuans;
	}
	public Set<TTDatTiec> getTtDatTiecs() {
		return ttDatTiecs;
	}
	public void setTtDatTiecs(Set<TTDatTiec> ttDatTiecs) {
		this.ttDatTiecs = ttDatTiecs;
	}
	public Set<Combo> getCombos() {
		return combos;
	}
	public void setCombos(Set<Combo> combos) {
		this.combos = combos;
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
	public String getGioitinh() {
		return gioitinh;
	}
	public void setGioitinh(String gioitinh) {
		this.gioitinh = gioitinh;
	}
	public Date getNgaysinh() {
		return ngaysinh;
	}
	public void setNgaysinh(Date ngaysinh) {
		this.ngaysinh = ngaysinh;
	}
	public String getNoisinh() {
		return noisinh;
	}
	public void setNoisinh(String noisinh) {
		this.noisinh = noisinh;
	}
	public String getSodienthoai() {
		return sodienthoai;
	}
	public void setSodienthoai(String sodienthoai) {
		this.sodienthoai = sodienthoai;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getNgaydangky() {
		return ngaydangky;
	}
	public void setNgaydangky(Date ngaydangky) {
		this.ngaydangky = ngaydangky;
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
	public String getSocmnd() {
		return socmnd;
	}
	public void setSocmnd(String socmnd) {
		this.socmnd = socmnd;
	}
	public String getLoai() {
		return loai;
	}
	public void setLoai(String loai) {
		this.loai = loai;
	}
	public String getMatkhau() {
		return matkhau;
	}
	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}
	public int getSolandat() {
		return solandat;
	}
	public void setSolandat(int solandat) {
		this.solandat = solandat;
	}
	public double getSodu() {
		return sodu;
	}
	public void setSodu(double sodu) {
		this.sodu = sodu;
	}
	public NguoiDung() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}