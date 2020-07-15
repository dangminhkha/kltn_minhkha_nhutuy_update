package com.example.demo.factorys;

import com.example.demo.entitys.CTNuocDatTiec;
import com.example.demo.utils.RandomStringExmple;

public class CTNuocDatTiecFactory {
	public static CTNuocDatTiec getInstance() {
		CTNuocDatTiec entity = new CTNuocDatTiec();
		String id = RandomStringExmple.id(6);
		entity.setId(id);
		return entity;
	}

}
