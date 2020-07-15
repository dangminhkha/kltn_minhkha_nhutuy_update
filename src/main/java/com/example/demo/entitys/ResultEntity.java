package com.example.demo.entitys;

public class ResultEntity<T> {
	private String ketqua;
	private T dulieu;
	private String noidung;
	public String getKetqua() {
		return ketqua;
	}
	public void setKetqua(String ketqua) {
		this.ketqua = ketqua;
	}
	public T getDulieu() {
		return dulieu;
	}
	public void setDulieu(T dulieu) {
		this.dulieu = dulieu;
	}
	public String getNoidung() {
		return noidung;
	}
	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}
	public ResultEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
