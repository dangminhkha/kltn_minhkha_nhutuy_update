package com.example.demo.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dtos.NhanVienDTO;
import com.example.demo.dtos.ThongKeDTO;
import com.example.demo.entitys.ResultEntity;
import com.example.demo.services.FilePDFService;
import com.example.demo.services.ThongKeService;
import com.example.demo.utils.StringUtil;

@Controller
@RequestMapping("/thongke")
public class ThongKeController {
	@Autowired
	private ThongKeService thongkeService;
	@Autowired
	private FilePDFService filePDFService;
	
	@GetMapping("")
	public String thongke(Model model, HttpSession session,
			@RequestParam(name = "thang", required = false, defaultValue = "0") Integer thang,
			@RequestParam(name = "nam", required = false, defaultValue = "0") Integer nam) {

		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		Date d = new Date();
		if (nam == 0) {
			nam = d.getYear() + 1900;
		}
		if (thang == 0) {
			thang = d.getMonth() + 1;
		}

		if (null != userDN && StringUtil.ADMIN.equals(userDN.getChucvu())) {
			ResultEntity<ThongKeDTO> result = thongkeService.thongke(nam, thang);
			session.setAttribute("thongke", result.getDulieu());
			session.setAttribute("thang", thang);
			session.setAttribute("nam", nam);
			return "admin_thongke";

		}
		return "redirect:/";

	}
	@GetMapping("/downloadpdf")
	public void downloadpdf(HttpServletResponse response,HttpSession session) {
		ThongKeDTO dto = (ThongKeDTO) session.getAttribute("thongke");
		int thang = (int)session.getAttribute("thang");
		int nam = (int)session.getAttribute("nam");
		
		String thoigian = "tháng "+thang+" năm "+nam;
		response.setContentType("application/pdf");
		String tenfile = "Thong-Ke-thang-"+thang+"-nam-"+nam;
		String headerKey =  "Content-Disposition";
		String headerValue = "attachment; filename="+tenfile+".pdf";
		response.setHeader(headerKey, headerValue);
		
		filePDFService.Export(dto,thoigian, response);
	}
}
