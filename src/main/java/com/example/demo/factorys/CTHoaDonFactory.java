package com.example.demo.factorys;

import com.example.demo.entitys.CTHoaDon;
import com.example.demo.utils.RandomStringExmple;

public class CTHoaDonFactory {
	public static CTHoaDon getInstance() {
		CTHoaDon entity = new CTHoaDon();
		String id = RandomStringExmple.id(6);
		entity.setId(id);
		return entity;
	}
}
