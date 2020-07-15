package com.example.demo.factorys;

import java.util.Date;

import com.example.demo.entitys.BangCong;
import com.example.demo.utils.RandomStringExmple;

public class BangCongFactory {
	public static BangCong getInstance() {
		BangCong entity = new BangCong();
		entity.setId(RandomStringExmple.id(6));
		entity.setNgaycong(new Date());
		entity.setLuong(0);
		return entity;
	}

}
