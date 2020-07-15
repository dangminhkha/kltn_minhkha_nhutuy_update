package com.example.demo.dtos;

import java.util.List;
import com.example.demo.entitys.Mon;

import lombok.Data;
@Data
public class LoaiMonDTO {
	private String id;
	private String ten;
	private String trangthai;
	private String loai;
	private List<MonDTO> mons;
	
	public String getId() {
		return id;
	}
	
	public String getLoai() {
		return loai;
	}

	public void setLoai(String loai) {
		this.loai = loai;
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

	public String getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}

	public List<MonDTO> getMons() {
		return mons;
	}

	public void setMons(List<MonDTO> mons) {
		this.mons = mons;
	}

	public LoaiMonDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
}
