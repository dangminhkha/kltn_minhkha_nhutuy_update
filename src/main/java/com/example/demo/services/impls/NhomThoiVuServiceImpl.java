package com.example.demo.services.impls;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.converters.NhomThoiVuConverter;
import com.example.demo.dtos.NhomThoiVuDTO;
import com.example.demo.entitys.NhanVien;
import com.example.demo.entitys.NhomThoiVu;
import com.example.demo.entitys.ResultEntity;
import com.example.demo.factorys.NhomThoiVuFactory;
import com.example.demo.repositorys.NhanVienRepository;
import com.example.demo.repositorys.NhomThoiVuRepository;
import com.example.demo.services.NhomThoiVuService;
import com.example.demo.utils.StringUtil;

@Service
public class NhomThoiVuServiceImpl implements NhomThoiVuService{

	@Autowired
	private NhomThoiVuRepository nhomThoiVuRepository;
	
	@Autowired
	private NhanVienRepository nhanvienRepository;
	
	@Override
	public ResultEntity<NhomThoiVuDTO> luuNhom(String nvId, NhomThoiVuDTO dto) {
		ResultEntity<NhomThoiVuDTO> result= new ResultEntity<>();
		Optional<NhanVien> nhanvienOptional = nhanvienRepository.findById(nvId);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dto.getNgaylam());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date startDay = calendar.getTime();
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 0);
		Date endDay = calendar.getTime();
		Optional<NhomThoiVu> nhomThoiVuOptional = nhomThoiVuRepository.timTen(dto.getTen(), StringUtil.DANGHOATDONG,startDay,endDay);
		if(nhomThoiVuOptional.isPresent()) {
			result.setKetqua(StringUtil.THATBAI);
			result.setNoidung("Nhóm này hôm nay đã tồn tại");
			return result;
		}
		if(nhanvienOptional.isPresent()) {
			
			NhomThoiVu entity = NhomThoiVuFactory.getInstance();
			entity.setTen(dto.getTen());
			entity.setCalam(dto.getCalam());
			entity.setLuong(dto.getLuong());
			entity.setNgaylam(dto.getNgaylam());
			nhomThoiVuRepository.save(entity);
			
			result.setDulieu(NhomThoiVuConverter.toDTO(entity));
			result.setKetqua(StringUtil.THANHCONG);
			result.setNoidung("Thành công");
			return result;
		}
		
		result.setKetqua(StringUtil.THATBAI);
		result.setNoidung("Thất bại");
		return result;
	}

	@Override
	public ResultEntity<List<NhomThoiVuDTO>> dsNhom(Date ngay) {
		ResultEntity<List<NhomThoiVuDTO>> result = new ResultEntity<>();
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(ngay);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date startDay = calendar.getTime();
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 0);
		Date endDay = calendar.getTime();
		List<NhomThoiVu> dsNhom = nhomThoiVuRepository.dsNhomTheoNgay(startDay,endDay);
		List<NhomThoiVuDTO> dsDTO = new ArrayList<>();
		if (dsNhom!=null && !dsNhom.isEmpty()) {
			dsDTO = dsNhom.stream().filter(x->x.getTrangthai().equals(StringUtil.DANGHOATDONG))
					.map(NhomThoiVuConverter::toDTO)
					.collect(Collectors.toList());
		}
		
		result.setKetqua(StringUtil.THANHCONG);
		result.setDulieu(dsDTO);
		result.setNoidung("OK");
		return result;
	}

	@Override
	public ResultEntity<NhomThoiVuDTO> timId(String id) {
		ResultEntity<NhomThoiVuDTO> result = new ResultEntity<>();
		Optional<NhomThoiVu> nhomOptional = nhomThoiVuRepository.timid(id, StringUtil.DANGHOATDONG);
		if (nhomOptional.isPresent()) {
			NhomThoiVuDTO dto = NhomThoiVuConverter.toDTO(nhomOptional.get());
			result.setDulieu(dto);
			result.setKetqua(StringUtil.THANHCONG);
			result.setNoidung("OK");
			return result;
		}
		result.setKetqua(StringUtil.THATBAI);
		result.setNoidung("Not found");
		return result;
	}

}
