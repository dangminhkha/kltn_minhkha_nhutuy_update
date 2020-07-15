package com.example.demo.services.impls;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.converters.NhanVienThoiVuConverter;
import com.example.demo.dtos.NhanVienThoiVuDTO;
import com.example.demo.entitys.NhanVienThoiVu;
import com.example.demo.entitys.NhomThoiVu;
import com.example.demo.entitys.ResultEntity;
import com.example.demo.factorys.NhanVienThoiVuFactory;
import com.example.demo.repositorys.NhanVienThoiVuRepository;
import com.example.demo.repositorys.NhomThoiVuRepository;
import com.example.demo.services.NhanVienThoiVuService;
import com.example.demo.utils.StringUtil;

@Service
public class NhanVienThoiVuServiceImpl implements NhanVienThoiVuService{
	@Autowired
	private NhanVienThoiVuRepository nhanVienThoiVuRepository;
	@Autowired
	private NhomThoiVuRepository nhomThoiVuRepository;
	
	@Override
	public ResultEntity<NhanVienThoiVuDTO> themNvtv(String nhomId,NhanVienThoiVuDTO dto) {
		ResultEntity<NhanVienThoiVuDTO> result = new ResultEntity<>();
		Optional<NhomThoiVu> nhomThoiVuOptional = nhomThoiVuRepository.findById(nhomId);
		if (nhomThoiVuOptional.isPresent()) {
			Optional<NhanVienThoiVu> nhanvienTVOptional = nhanVienThoiVuRepository.timCMND(nhomId, dto.getCmnd(), StringUtil.DANGHOATDONG);
			if (nhanvienTVOptional.isPresent()) {
				result.setKetqua(StringUtil.THATBAI);
				result.setNoidung("Đã có nhân viên trong nhóm");
				return result;
			}
			
			NhanVienThoiVu entity = NhanVienThoiVuFactory.getInstance();
			entity.setCmnd(dto.getCmnd());
			entity.setNhomThoiVu(nhomThoiVuOptional.get());
			entity.setSodienthoai(dto.getSodienthoai());
			entity.setTen(dto.getTen());
			nhanVienThoiVuRepository.save(entity);
			result.setDulieu(NhanVienThoiVuConverter.toDTO(entity));
			result.setKetqua(StringUtil.THANHCONG);
			result.setNoidung("OK");
			return result;
		}
		result.setKetqua(StringUtil.THATBAI);
		result.setNoidung("Nhóm không tồn tại");
		return result;
	}

}
