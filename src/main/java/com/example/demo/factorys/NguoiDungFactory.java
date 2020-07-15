package com.example.demo.factorys;

import java.util.Date;

import com.example.demo.entitys.NguoiDung;
import com.example.demo.utils.StringUtil;

public class NguoiDungFactory {
	public static NguoiDung getInstance() {
		NguoiDung entity = new NguoiDung();
		Date d = new Date();
		entity.setNgaycapnhat(d);
		entity.setNgaydangky(d);
		entity.setTrangthai(StringUtil.DANGHOATDONG);
		entity.setSodu(0);
		entity.setLoai(StringUtil.KHACHHANG);
		entity.setSolandat(0);
		return entity;
	}

}
