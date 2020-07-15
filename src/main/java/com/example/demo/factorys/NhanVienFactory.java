package com.example.demo.factorys;

import java.util.Date;

import com.example.demo.entitys.NhanVien;
import com.example.demo.utils.StringUtil;

public class NhanVienFactory {
	public static NhanVien getInstance() {
		Date d = new Date();
		NhanVien entity = new NhanVien();
		entity.setNgaytao(d);
		entity.setNgaycapnhat(d);
		entity.setTrangthai(StringUtil.DANGHOATDONG);
		entity.setLuong(0);
		return entity;
	}

}
