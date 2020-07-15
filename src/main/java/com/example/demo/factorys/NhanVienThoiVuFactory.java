package com.example.demo.factorys;

import java.util.Date;

import com.example.demo.entitys.NhanVienThoiVu;
import com.example.demo.utils.RandomStringExmple;
import com.example.demo.utils.StringUtil;

public class NhanVienThoiVuFactory {
	public static NhanVienThoiVu getInstance() {
		NhanVienThoiVu entity = new NhanVienThoiVu();
		String id = RandomStringExmple.id(6);
		entity.setId(id);
		entity.setTrangthai(StringUtil.DANGHOATDONG);
		entity.setThoigianvao(new Date());
		return entity;
	}
}
