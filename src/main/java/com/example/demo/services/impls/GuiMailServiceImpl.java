package com.example.demo.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.entitys.ResultEntity;
import com.example.demo.services.GuiMailService;
import com.example.demo.utils.StringUtil;

@Service
public class GuiMailServiceImpl implements GuiMailService{

	@Autowired
	private JavaMailSender javaMailSender;
	@Override
	public ResultEntity<String> guimail(String nguoinhan, String tieude, String noidung) {
		ResultEntity<String> result = new ResultEntity<>();
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(nguoinhan);
			message.setSubject(tieude);
			message.setText(noidung);
			javaMailSender.send(message);
			result.setDulieu("Đã gửi mail");
			result.setKetqua(StringUtil.THANHCONG);
			result.setNoidung("ok");
			return result;
		} catch (Exception e) {
			// TODO: handle exception
		}
		result.setDulieu("Chưa gửi mail");
		result.setKetqua(StringUtil.THANHCONG);
		result.setNoidung("Lỗi gửi mail");
		return result;
	}

}
