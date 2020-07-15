package com.example.demo.dtos;

public class CTHoaDonDTO {
	private String id;
	private ComboDTO combo;
	private MonDTO mon;
	private String ten;
	private int soluong;
	private double gia;
	private String giaFM;
	private String giaDon;
	
	
	public String getGiaDon() {
		return giaDon;
	}
	public void setGiaDon(String giaDon) {
		this.giaDon = giaDon;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public String getGiaFM() {
		return giaFM;
	}
	public void setGiaFM(String giaFM) {
		this.giaFM = giaFM;
	}
	public CTHoaDonDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ComboDTO getCombo() {
		return combo;
	}
	public void setCombo(ComboDTO combo) {
		this.combo = combo;
	}
	public MonDTO getMon() {
		return mon;
	}
	public void setMon(MonDTO mon) {
		this.mon = mon;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	public double getGia() {
		return gia;
	}
	public void setGia(double gia) {
		this.gia = gia;
	}
	@Override
	public String toString() {
		return "CTHoaDonDTO [id=" + id + ", combo=" + combo + ", mon=" + mon + ", soluong=" + soluong + ", gia=" + gia
				+ "]";
	}
	
	
}
