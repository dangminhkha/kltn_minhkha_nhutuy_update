package com.example.demo.services.impls;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.converters.ThongKeConverter;
import com.example.demo.dtos.ThongKeDTO;
import com.example.demo.entitys.BangCong;
import com.example.demo.entitys.HoaDon;
import com.example.demo.entitys.Luong;
import com.example.demo.entitys.NhomThoiVu;
import com.example.demo.entitys.ResultEntity;
import com.example.demo.entitys.TTDatTiec;
import com.example.demo.repositorys.BangCongRepository;
import com.example.demo.repositorys.HoaDonRepository;
import com.example.demo.repositorys.LuongRepository;
import com.example.demo.repositorys.NhomThoiVuRepository;
import com.example.demo.repositorys.TTDatTiecRepository;
import com.example.demo.services.ThongKeService;
import com.example.demo.utils.StringUtil;
import com.ibm.icu.text.SimpleDateFormat;

@Service
public class ThongKeServiceImpl implements ThongKeService{
	
	@Autowired
	private TTDatTiecRepository ttdattiecRepository;
	@Autowired
	private HoaDonRepository hoaDonRepository;
	@Autowired
	private NhomThoiVuRepository nhomThoiVuRepository;
	@Autowired
	private BangCongRepository bangcongRepository;
	@Autowired
	private LuongRepository luongRepository;

	@Override
	public ResultEntity<ThongKeDTO> thongke(int nam, int thang) {
		ResultEntity<ThongKeDTO> result = new ResultEntity<>();
		thang--;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.YEAR, nam);
		calendar.set(Calendar.MONTH, thang);
		calendar.set(Calendar.DATE, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.AM_PM, 0);
		Date startDay = calendar.getTime();
		if (thang == 12) {
			calendar.set(Calendar.YEAR, nam +1);
			calendar.set(Calendar.MONDAY, 1);
		}else {
			calendar.set(Calendar.MONDAY, thang+1);
		}
		calendar.set(Calendar.HOUR, 11);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.AM_PM, 1);
		Date endDay = calendar.getTime();
		endDay.setDate(endDay.getDate()-1);
				
		List<TTDatTiec> dsTTDatTiec = ttdattiecRepository.dsDDThongKe(startDay, endDay);
		List<HoaDon> dsHoaDon = hoaDonRepository.dsHDThongKe(startDay, endDay);
		List<BangCong> dsCong = bangcongRepository.dsCongTheoNgay(StringUtil.DANGHOATDONG, StringUtil.NHANVIENTHOIVU, startDay, endDay);
		List<Luong> dsLuong = luongRepository.dsTheoNamThang(nam, thang+1);
		
		ThongKeDTO dto = ThongKeConverter.toDTO(dsTTDatTiec, dsHoaDon, dsCong,dsLuong);
		result.setDulieu(dto);
		result.setKetqua(StringUtil.THANHCONG);
		result.setNoidung("OK");
		return result;
	}

}
