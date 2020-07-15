package com.example.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUI {
 public static Date formatDateUI(String date,String loai) {
	 Date d;
	 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			d = format.parse(date);
			System.out.println(d);
			if(loai.equals(StringUtil.MOI)) {
				d.setDate(d.getDate()+1);
			}
		} catch (ParseException e) {
			d = new Date();
		}
		return d;
 }
}
