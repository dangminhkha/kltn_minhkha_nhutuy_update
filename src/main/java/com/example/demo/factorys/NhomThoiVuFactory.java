package com.example.demo.factorys;

import java.util.Date;
import java.util.HashSet;

import com.example.demo.entitys.NhomThoiVu;
import com.example.demo.utils.RandomStringExmple;
import com.example.demo.utils.StringUtil;

public class NhomThoiVuFactory {
	public static NhomThoiVu getInstance() {
		NhomThoiVu entity = new NhomThoiVu();
		String id = RandomStringExmple.id(6);
		entity.setId(id);
		entity.setTrangthai(StringUtil.DANGHOATDONG);
		entity.setNgaylam(new Date());
		System.out.println(new Date());
		entity.setNhanVienThoiVus(new HashSet<>());
		return entity;
	}

}
