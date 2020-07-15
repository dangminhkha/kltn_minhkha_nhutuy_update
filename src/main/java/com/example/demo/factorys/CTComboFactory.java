package com.example.demo.factorys;

import com.example.demo.entitys.CTCombo;
import com.example.demo.utils.RandomStringExmple;
import com.example.demo.utils.StringUtil;

public class CTComboFactory {
	public static CTCombo getInstance() {
		String id = RandomStringExmple.id(6);
		CTCombo entity = new CTCombo();
		entity.setId(id);
		entity.setTrangthai(StringUtil.DANGHOATDONG);
		return entity;
	}

}
