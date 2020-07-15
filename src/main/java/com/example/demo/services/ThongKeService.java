package com.example.demo.services;

import com.example.demo.dtos.ThongKeDTO;
import com.example.demo.entitys.ResultEntity;

public interface ThongKeService {
	ResultEntity<ThongKeDTO> thongke(int nam,int thang);

}
