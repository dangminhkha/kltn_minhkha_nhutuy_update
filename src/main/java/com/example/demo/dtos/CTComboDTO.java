package com.example.demo.dtos;


public class CTComboDTO {
	private String id;
	private MonDTO mon;
	private int stt;
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
	public int getStt() {
		return stt;
	}
	public void setStt(int stt) {
		this.stt = stt;
	}
	public CTComboDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CTComboDTO [id=" + id + ", mon=" + mon + ", stt=" + stt + "]";
	}
	
	
}
