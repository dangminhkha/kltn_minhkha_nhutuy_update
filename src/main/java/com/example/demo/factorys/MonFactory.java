package com.example.demo.factorys;

import java.util.Date;

import com.example.demo.entitys.Mon;
import com.example.demo.utils.StringUtil;

public class MonFactory {
	public static Mon getInstance() {
		Date d = new Date();
		Mon entity = new Mon();
		entity.setTrangthai(StringUtil.DANGHOATDONG);
		entity.setNgaytao(d);
		entity.setNgaycapnhat(d);
		return entity;
	}
}
