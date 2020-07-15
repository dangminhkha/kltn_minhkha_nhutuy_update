package com.example.demo.factorys;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import com.example.demo.entitys.Combo;
import com.example.demo.utils.StringUtil;

public class ComboFactory {
	public static Combo getInstance() {
		Combo entity = new Combo();
		Date d = new Date();
		entity.setNgaycapnhat(d);
		entity.setNgaytao(d);
		entity.setTrangthai(StringUtil.DANGHOATDONG);
		entity.setCtcombos(new HashSet<>());
		return entity;
	}

}
