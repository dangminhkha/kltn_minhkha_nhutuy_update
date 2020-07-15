package com.example.demo.entitys;

import java.util.Collection;
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
@Table(name = "nhanvien")
@Indexed
public class NhanVien {
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
	@Column(name = "chucvu")
	private String chucvu;
	@Column(name = "socmnd")
	private String socmnd;
	@Column(name = "trangthai")
	private String trangthai;
	@Column(name = "matkhau")
	private String matkhau;
	@Column(name = "luong")
	private double luong;
	@Column(name = "ngaytao")
	private Date ngaytao;
	@Column(name = "ngaycapnhat")
	private Date ngaycapnhat;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "nhanvien")
	private Set<Mon> mons;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "nhanvien")
	private Set<Sanh> sanhs;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "nhanvien")
	private Set<Combo> combos;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "nhanvien")
	private Set<HoaDon> hoadons;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "nhanvien")
	private Set<Luong> luongs;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "nhanvien")
	private Set<BangCong> bangCongs;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "nhanvien")
	private Set<BinhLuan> binhLuans;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "nhanvien")
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

	public Set<BangCong> getBangCongs() {
		return bangCongs;
	}

	public void setBangCongs(Set<BangCong> bangCongs) {
		this.bangCongs = bangCongs;
	}

	public Set<Luong> getLuongs() {
		return luongs;
	}

	public void setLuongs(Set<Luong> luongs) {
		this.luongs = luongs;
	}

	public Set<HoaDon> getHoadons() {
		return hoadons;
	}

	public void setHoadons(Set<HoaDon> hoadons) {
		this.hoadons = hoadons;
	}

	public Set<Combo> getCombos() {
		return combos;
	}

	public void setCombos(Set<Combo> combos) {
		this.combos = combos;
	}

	public Set<Sanh> getSanhs() {
		return sanhs;
	}

	public void setSanhs(Set<Sanh> sanhs) {
		this.sanhs = sanhs;
	}

	public Set<Mon> getMons() {
		return mons;
	}

	public void setMons(Set<Mon> mons) {
		this.mons = mons;
	}

	public NhanVien() {
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

	public String getChucvu() {
		return chucvu;
	}

	public void setChucvu(String chucvu) {
		this.chucvu = chucvu;
	}

	public String getSocmnd() {
		return socmnd;
	}

	public void setSocmnd(String socmnd) {
		this.socmnd = socmnd;
	}

	public String getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}

	public String getMatkhau() {
		return matkhau;
	}

	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}

	public double getLuong() {
		return luong;
	}

	public void setLuong(double luong) {
		this.luong = luong;
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

}
