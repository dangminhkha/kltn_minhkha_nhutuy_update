package com.example.demo.utils;

import org.springframework.ui.Model;

public class PageConverter {
	public static void pageFormat(Model model,int page,int slPage) {
		int page1 = page-2;
		int page2 = page-1;
		int page4 = page+1;
		int page5 = page+2;
		if (page1>=0) {
			model.addAttribute("page1", page1);
		}
		if (page2>=0) {
			model.addAttribute("page2", page2);
		}
		if (page4<slPage) {
			model.addAttribute("page4", page4);
		}
		if (page5<slPage) {
			model.addAttribute("page5", page5);
		}
		if(page>0) {
			model.addAttribute("pageTruoc", page-1);
		}else {
			model.addAttribute("pageTruoc", page);
		}
		if(slPage>page+1) {
			model.addAttribute("pageSau", page+1);
		}else {
			model.addAttribute("pageSau", page);
		}
		model.addAttribute("pageHienTai", page);
	}

}
