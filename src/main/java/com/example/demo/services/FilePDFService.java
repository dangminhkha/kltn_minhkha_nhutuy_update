package com.example.demo.services;

import javax.servlet.http.HttpServletResponse;

import com.example.demo.dtos.ThongKeDTO;

public interface FilePDFService {
	
	public void Export(ThongKeDTO thongke,String thoigian, HttpServletResponse response);

}
