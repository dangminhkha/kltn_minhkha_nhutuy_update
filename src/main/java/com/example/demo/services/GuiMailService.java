package com.example.demo.services;

import com.example.demo.entitys.ResultEntity;

public interface GuiMailService {
	ResultEntity<String> guimail(String nguoinhan, String tieude, String noidung);

}
