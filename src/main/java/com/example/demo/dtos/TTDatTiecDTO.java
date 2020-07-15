package com.example.demo.dtos;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.example.demo.entitys.CTNuocDatTiec;

public class TTDatTiecDTO {
	private String id;
	private NguoiDungDTO nguoidung;
	private SanhDTO sanh;
	private ComboDTO combo;
	private String ngaytochuc;
	private String catochuc;
	private String trangthai;
	private Date ngaytao;
	private Date ngaycapnhat;
	private String ghichu;
	private double tiencoc;
	private int sokhach;
	private int soban;
	private String loai;
	private String tthientai;
	private String tiencocFM;
	private double giauocluong;
	private String giauocluongFM;
	private List<CTNuocDatTiecDTO> ctNuocDatTiecs;
	
	
	public String getGiauocluongFM() {
		return giauocluongFM;
	}
	public void setGiauocluongFM(String giauocluongFM) {
		this.giauocluongFM = giauocluongFM;
	}
	public double getGiauocluong() {
		return giauocluong;
	}
	public void setGiauocluong(double giauocluong) {
		this.giauocluong = giauocluong;
	}
	public String getTiencocFM() {
		return tiencocFM;
	}
	public void setTiencocFM(String tiencocFM) {
		this.tiencocFM = tiencocFM;
	}
	public List<CTNuocDatTiecDTO> getCtNuocDatTiecs() {
		return ctNuocDatTiecs;
	}
	public void setCtNuocDatTiecs(List<CTNuocDatTiecDTO> ctNuocDatTiecs) {
		this.ctNuocDatTiecs = ctNuocDatTiecs;
	}
	public String getTthientai() {
		return tthientai;
	}
	public void setTthientai(String tthientai) {
		this.tthientai = tthientai;
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
	
	public NguoiDungDTO getNguoidung() {
		return nguoidung;
	}
	public void setNguoidung(NguoiDungDTO nguoidung) {
		this.nguoidung = nguoidung;
	}
	public SanhDTO getSanh() {
		return sanh;
	}
	public void setSanh(SanhDTO sanh) {
		this.sanh = sanh;
	}
	public ComboDTO getCombo() {
		return combo;
	}
	public void setCombo(ComboDTO combo) {
		this.combo = combo;
	}
	public String getNgaytochuc() {
		return ngaytochuc;
	}
	public void setNgaytochuc(String ngaytochuc) {
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
	public TTDatTiecDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "TTDatTiecDTO [id=" + id + ", nguoidung=" + nguoidung + ", sanh=" + sanh + ", combo=" + combo
				+ ", ngaytochuc=" + ngaytochuc + ", catochuc=" + catochuc + ", trangthai=" + trangthai + ", ngaytao="
				+ ngaytao + ", ngaycapnhat=" + ngaycapnhat + ", ghichu=" + ghichu + ", tiencoc=" + tiencoc
				+ ", sokhach=" + sokhach + ", soban=" + soban + ", loai=" + loai + ", tthientai=" + tthientai
				+ ", ctNuocDatTiecs=" + ctNuocDatTiecs + "]";
	}
	
	
}
