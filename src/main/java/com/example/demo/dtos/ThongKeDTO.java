package com.example.demo.dtos;

public class ThongKeDTO {
	private int tongdondattiec;
	private int tongdondathanhtoan;
	private int tongdondahuy;
	private double tongtiendathanhtoan;
	private double tongluongnhanvienchinhthuc;
	private double tongluongnhanvienthoivu;
	private String tongtiendathanhtoanFM;
	private String tongluongnhanvienchinhthucFM;
	private String tongluongnhanvienthoivuFM;
	
	public ThongKeDTO() {
		super();
		this.tongdondahuy = 0;
		this.tongdondathanhtoan = 0;
		this.tongdondattiec = 0;
		this.tongluongnhanvienthoivu = 0;
		this.tongtiendathanhtoan = 0;
		this.tongluongnhanvienchinhthuc = 0;
		// TODO Auto-generated constructor stub
	}

	public int getTongdondattiec() {
		return tongdondattiec;
	}

	public void setTongdondattiec(int tongdondattiec) {
		this.tongdondattiec = tongdondattiec;
	}

	public int getTongdondathanhtoan() {
		return tongdondathanhtoan;
	}

	public void setTongdondathanhtoan(int tongdondathanhtoan) {
		this.tongdondathanhtoan = tongdondathanhtoan;
	}

	public int getTongdondahuy() {
		return tongdondahuy;
	}

	public void setTongdondahuy(int tongdondahuy) {
		this.tongdondahuy = tongdondahuy;
	}

	public double getTongtiendathanhtoan() {
		return tongtiendathanhtoan;
	}

	public void setTongtiendathanhtoan(double tongtiendathanhtoan) {
		this.tongtiendathanhtoan = tongtiendathanhtoan;
	}

	public double getTongluongnhanvienchinhthuc() {
		return tongluongnhanvienchinhthuc;
	}

	public void setTongluongnhanvienchinhthuc(double tongluongnhanvienchinhthuc) {
		this.tongluongnhanvienchinhthuc = tongluongnhanvienchinhthuc;
	}

	public double getTongluongnhanvienthoivu() {
		return tongluongnhanvienthoivu;
	}

	public void setTongluongnhanvienthoivu(double tongluongnhanvienthoivu) {
		this.tongluongnhanvienthoivu = tongluongnhanvienthoivu;
	}

	public String getTongtiendathanhtoanFM() {
		return tongtiendathanhtoanFM;
	}

	public void setTongtiendathanhtoanFM(String tongtiendathanhtoanFM) {
		this.tongtiendathanhtoanFM = tongtiendathanhtoanFM;
	}

	public String getTongluongnhanvienchinhthucFM() {
		return tongluongnhanvienchinhthucFM;
	}

	public void setTongluongnhanvienchinhthucFM(String tongluongnhanvienchinhthucFM) {
		this.tongluongnhanvienchinhthucFM = tongluongnhanvienchinhthucFM;
	}

	public String getTongluongnhanvienthoivuFM() {
		return tongluongnhanvienthoivuFM;
	}

	public void setTongluongnhanvienthoivuFM(String tongluongnhanvienthoivuFM) {
		this.tongluongnhanvienthoivuFM = tongluongnhanvienthoivuFM;
	}
	
	
	
	
}
