package com.example.demo.factorys;

import java.util.Date;

import com.example.demo.entitys.Sanh;
import com.example.demo.utils.StringUtil;

public class SanhFactory {
	public static Sanh getInstane() {
		Date d = new Date();
		Sanh entity = new Sanh();
		entity.setNgaycapnhat(d);
		entity.setNgaytao(d);
		entity.setTrangthai(StringUtil.DANGHOATDONG);
		return entity;
	}

}
