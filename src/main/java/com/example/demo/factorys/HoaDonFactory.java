package com.example.demo.factorys;

import java.util.Date;
import java.util.HashSet;

import com.example.demo.entitys.CTHoaDon;
import com.example.demo.entitys.HoaDon;
import com.example.demo.utils.RandomStringExmple;
import com.example.demo.utils.StringUtil;

public class HoaDonFactory {
	public static HoaDon getInstance() {
		HoaDon entity = new HoaDon();
		String id = RandomStringExmple.id(6);
		entity.setId(id);
		entity.setTrangthai(StringUtil.DATHANHTOAN);
		entity.setCtHoaDons(new HashSet<CTHoaDon>());
		entity.setNgaythanhtoan(new Date());
		return entity;
	}

}
