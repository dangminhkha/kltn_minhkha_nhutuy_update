package com.example.demo.factorys;

import java.util.Date;
import java.util.HashSet;

import com.example.demo.entitys.TTDatTiec;
import com.example.demo.utils.StringUtil;

public class TTDatTiecFactory {
	public static TTDatTiec getInstance() {
		TTDatTiec entity = new TTDatTiec();
		Date d = new Date();
		entity.setTrangthai(StringUtil.DANGCHODUYET);
		entity.setNgaytao(d);
		entity.setNgaycapnhat(d);
		entity.setCtNuocDatTiecs(new HashSet<>());
		return entity;
	}
}
