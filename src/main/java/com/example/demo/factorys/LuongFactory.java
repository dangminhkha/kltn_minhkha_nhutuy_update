package com.example.demo.factorys;

import com.example.demo.entitys.Luong;
import com.example.demo.utils.RandomStringExmple;

public class LuongFactory {
	public static Luong getInstance() {
		Luong entity = new Luong();
		String id = RandomStringExmple.id(6);
		entity.setId(id);
		return entity;
	}
}
