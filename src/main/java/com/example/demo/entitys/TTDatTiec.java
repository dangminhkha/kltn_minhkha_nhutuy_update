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
@Table(name = "ttdattiec")
public class TTDatTiec {
	@Id
	private String id;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
	@JoinColumn(name = "nguoidungid",referencedColumnName = "id")
	private NguoiDung nguoidung;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
	@JoinColumn(name = "sanhid",referencedColumnName = "id")
	private Sanh sanh;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
	@JoinColumn(name = "comboid",referencedColumnName = "id")
	private Combo combo;
	
	@Column(name = "ngaytochuc")
	private Date ngaytochuc;
	@Column(name = "catochuc")
	private String catochuc;
	@Column(name = "trangthai")
	private String trangthai;
	@Column(name = "ngaytao")
	private Date ngaytao;
	@Column(name = "ngaycapnhat")
	private Date ngaycapnhat;
	@Column(name = "ghichu")
	private String ghichu;
	@Column(name = "tiencoc")
	private double tiencoc;
	@Column(name = "sokhach")
	private int sokhach;
	@Column(name = "soban")
	private int soban;
	@Column(name = "loai")
	private String loai;
	
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="ttDatTiec")
	private Set<CTNuocDatTiec> ctNuocDatTiecs;
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="ttDatTiec")
	private Set<HoaDon> hoadons;
	
	public Set<HoaDon> getHoadons() {
		return hoadons;
	}
	public void setHoadons(Set<HoaDon> hoadons) {
		this.hoadons = hoadons;
	}
	public Set<CTNuocDatTiec> getCtNuocDatTiecs() {
		return ctNuocDatTiecs;
	}
	public void setCtNuocDatTiecs(Set<CTNuocDatTiec> ctNuocDatTiecs) {
		this.ctNuocDatTiecs = ctNuocDatTiecs;
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

	public NguoiDung getNguoidung() {
		return nguoidung;
	}
	public void setNguoidung(NguoiDung nguoidung) {
		this.nguoidung = nguoidung;
	}
	public Sanh getSanh() {
		return sanh;
	}
	public void setSanh(Sanh sanh) {
		this.sanh = sanh;
	}
	public Combo getCombo() {
		return combo;
	}
	public void setCombo(Combo combo) {
		this.combo = combo;
	}
	public Date getNgaytochuc() {
		return ngaytochuc;
	}
	public void setNgaytochuc(Date ngaytochuc) {
		this.ngaytochuc = ngaytochuc;
	}
	public String getCatochuc() {
		return catochuc;
	}
	public void setCatochuc(String catochuc) {
		this.catochuc = catochuc;
	}
	public String getTrangthai() {
		return trangthai;
	}
	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
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
	public String getGhichu() {
		return ghichu;
	}
	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
	}
	public double getTiencoc() {
		return tiencoc;
	}
	public void setTiencoc(double tiencoc) {
		this.tiencoc = tiencoc;
	}
	public int getSokhach() {
		return sokhach;
	}
	public void setSokhach(int sokhach) {
		this.sokhach = sokhach;
	}
	public int getSoban() {
		return soban;
	}
	public void setSoban(int soban) {
		this.soban = soban;
	}
	public TTDatTiec() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
