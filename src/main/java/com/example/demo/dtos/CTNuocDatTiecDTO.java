package com.example.demo.dtos;

public class CTNuocDatTiecDTO {
	private String id;
	private MonDTO mon;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public MonDTO getMon() {
		return mon;
	}
	public void setMon(MonDTO mon) {
		this.mon = mon;
	}
	public CTNuocDatTiecDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CTNuocDatTiecDTO [id=" + id + ", mon=" + mon + "]";
	}
	
}
