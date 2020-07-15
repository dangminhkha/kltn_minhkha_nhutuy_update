package com.example.demo.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dtos.LoaiMonDTO;
import com.example.demo.dtos.NguoiDungDTO;
import com.example.demo.dtos.NhanVienDTO;
import com.example.demo.entitys.ResultEntity;
import com.example.demo.services.DangNhapService;
import com.example.demo.services.LoaiMonService;
import com.example.demo.utils.StringUtil;

@Controller
@RequestMapping("/dangnhap")
public class DangNhapController {
	@Autowired
	private DangNhapService dangNhapService;
	@Autowired
	private LoaiMonService loaiMonService;
	@PostMapping("")
	public String login(@Valid String username,@Valid String pass,HttpSession session,Model model) {
		ResultEntity<NguoiDungDTO> result = dangNhapService.dangnhap(username, pass);
		model.addAttribute("thongbao", result.getNoidung());
		if (StringUtil.THANHCONG.equals(result.getKetqua())) {
			session.setAttribute("userDangNhap", result.getDulieu());
			session.removeAttribute("username");
			session.removeAttribute("pass");
			return "redirect:/";
		}else {
			session.setAttribute("username", username);
			session.setAttribute("pass", pass);
			return "dangnhap";
		}
	}
	
	
	@PostMapping("admin")
	public String admin(@Valid String username,@Valid String pass,HttpSession session,Model model) {
		ResultEntity<NhanVienDTO> result = dangNhapService.nvDN(username, pass);
		session.setAttribute("message", result.getNoidung());

		if(StringUtil.THANHCONG.equals(result.getKetqua()) && null!=result.getDulieu()) {
			session.setAttribute("userDangNhap", result.getDulieu());
			if(session.getAttribute("loaimon")==null) {
				ResultEntity<List<LoaiMonDTO>> resultLoai = loaiMonService.loaiMonAll();
				session.setAttribute("loaimon", resultLoai.getDulieu());
			}
			session.removeAttribute("username");
			session.removeAttribute("pass");
			session.removeAttribute("message");
			return "redirect:/";
		}else {
			session.setAttribute("username", username);
			session.setAttribute("pass", pass);
			return "redirect:/admin";
		}
	}
	@GetMapping("/dangxuat")
	public String dangxuat(HttpSession session) {
		session.removeAttribute("userDangNhap");
		return "redirect:/";
	}

}
