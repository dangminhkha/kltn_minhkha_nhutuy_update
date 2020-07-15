package com.example.demo.factorys;

import com.example.demo.entitys.LoaiMon;
import com.example.demo.utils.StringUtil;

public class LoaiMonFactory {
	public static LoaiMon getInstance() {
		LoaiMon entity = new LoaiMon();
		entity.setTrangthai(StringUtil.DANGHOATDONG);
		return entity;
	}
}
