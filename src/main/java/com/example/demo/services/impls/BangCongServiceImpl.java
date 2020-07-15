package com.example.demo.services.impls;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.converters.BangCongConverter;
import com.example.demo.converters.NhanVienConverter;
import com.example.demo.dtos.BangCongDTO;
import com.example.demo.dtos.CTComboDTO;
import com.example.demo.dtos.NhanVienDTO;
import com.example.demo.entitys.BangCong;
import com.example.demo.entitys.NhanVien;
import com.example.demo.entitys.ResultEntity;
import com.example.demo.factorys.BangCongFactory;
import com.example.demo.repositorys.BangCongRepository;
import com.example.demo.repositorys.NhanVienRepository;
import com.example.demo.services.BangCongService;
import com.example.demo.utils.StringUtil;
import com.ibm.icu.text.SimpleDateFormat;

@Service
public class BangCongServiceImpl implements BangCongService{

	@Autowired
	private BangCongRepository bangCongRepository;
	@Autowired
	private NhanVienRepository nhanVienRepository;
	
	@Override
	public ResultEntity<BangCongDTO> chamCong(BangCongDTO dto) {
		ResultEntity<BangCongDTO> result = new ResultEntity<>();
		Optional<NhanVien> nhanVienOptional = nhanVienRepository.findById(dto.getNhanvien().getId());
		if (nhanVienOptional.isPresent()) {
			BangCong entity = BangCongFactory.getInstance();
			entity.setNgaycong(dto.getNgaycong());
			entity.setNhanvien(nhanVienOptional.get());
			entity.setCong(dto.getCong());
			System.out.println(nhanVienOptional.get().getChucvu());
			if (nhanVienOptional.get().getChucvu().equals(StringUtil.NHANVIENTHOIVU)) {
				
				double luong = 0;
				if (dto.getCong().equals(StringUtil.CANGAY)) {
					luong = nhanVienOptional.get().getLuong()*2;
				}else {
					luong = nhanVienOptional.get().getLuong();
				}
				entity.setLuong(luong);
			}
			
			bangCongRepository.save(entity);
			result.setDulieu(BangCongConverter.toDTO(entity));
			result.setKetqua(StringUtil.THANHCONG);
			result.setNoidung("OK");
			return result;
		}
		
		result.setKetqua(StringUtil.THATBAI);
		result.setNoidung("Không tìm thấy nhân viên");
		return result;	
		}

	@Override
	public ResultEntity<List<BangCongDTO>> dsCongNhanVien(String nvId, int thang, int nam) {
		ResultEntity<List<BangCongDTO>> resut = new ResultEntity<>();
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
		
		List<BangCong> dsCongNV = bangCongRepository.dsCongNhanVien(nvId, startDay, endDay);
		
		List<BangCongDTO> dsDTO  = dsCongNV.stream().map(BangCongConverter::toDTO).collect(Collectors.toList());
		
		Collections.sort(dsDTO, new Comparator<BangCongDTO>() {

			@Override
			public int compare(BangCongDTO o1, BangCongDTO o2) {
				if(o1.getNgaycong().compareTo(o2.getNgaycong())==-1) {
					return -1;
				}
				return 0;
			}
		});
		
		resut.setDulieu(dsDTO);
		resut.setKetqua(StringUtil.THANHCONG);
		resut.setNoidung("OK");
		return resut;
	}

	@Override
	public ResultEntity<List<BangCongDTO>> dsCongTheoNgay(Date ngaycong, String trangthai, String chucvu) {
		ResultEntity<List<BangCongDTO>> result = new ResultEntity<>();
		SimpleDateFormat sfm = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(ngaycong);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date startDay = calendar.getTime();
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 0);
		Date endDay = calendar.getTime();
		
		System.out.println(sfm.format(startDay));
		System.out.println(sfm.format(endDay));
		
		
		List<BangCong> dsCongtheongay = bangCongRepository.dsCongTheoNgay(trangthai, chucvu, startDay, endDay);
		List<BangCongDTO> dsDTO = dsCongtheongay.stream().map(BangCongConverter::toDTO).collect(Collectors.toList());
		
		System.out.println(dsDTO);
		
		Collections.sort(dsDTO, new Comparator<BangCongDTO>() {

			@Override
			public int compare(BangCongDTO o1, BangCongDTO o2) {
				if(o1.getCong().compareTo(o2.getCong())==-1) {
					return -1;
				}
				return 0;
			}
		});
		
		result.setDulieu(dsDTO);
		result.setKetqua(StringUtil.THANHCONG);
		result.setNoidung("OK");
		return result;
	}
	

}
