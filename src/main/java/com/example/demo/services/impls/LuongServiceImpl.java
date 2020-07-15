package com.example.demo.services.impls;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.converters.LuongConverter;
import com.example.demo.dtos.LuongDTO;
import com.example.demo.entitys.Luong;
import com.example.demo.entitys.NhanVien;
import com.example.demo.entitys.ResultEntity;
import com.example.demo.factorys.LuongFactory;
import com.example.demo.repositorys.LuongRepository;
import com.example.demo.repositorys.NhanVienRepository;
import com.example.demo.services.LuongService;
import com.example.demo.utils.StringUtil;

@Service
public class LuongServiceImpl implements LuongService{
	@Autowired
	private LuongRepository luongRepository;
	@Autowired
	private NhanVienRepository nhanvienRepository;
	
	@Override
	public ResultEntity<List<LuongDTO>> tinhLuong(List<LuongDTO> dstinhluong) {
		ResultEntity<List<LuongDTO>> result = new ResultEntity<>();
		int nam = dstinhluong.get(0).getNam();
		int thang = dstinhluong.get(0).getThang();
		List<Luong> luong = luongRepository.dsTheoNamThang(nam, thang);
		if (luong!=null && !luong.isEmpty()) {
			result.setKetqua(StringUtil.THATBAI);
			result.setNoidung("Lương tháng này đã được tính");
			return result;
		}
		
		List<LuongDTO> dsDTO = dstinhluong.stream().map(x->{
			Optional<NhanVien> nvOptional = nhanvienRepository.findById(x.getNhanvien().getId());			
			Luong entity = LuongFactory.getInstance();
			entity.setNam(x.getNam());
			entity.setNgaycongthang(x.getNgaycongthang());
			entity.setNhanvien(nvOptional.get());
			entity.setSongaycong(x.getSongaycong());
			entity.setThang(x.getThang());
			double tienluong = (nvOptional.get().getLuong()/x.getNgaycongthang())*x.getSongaycong();
			entity.setTienluong(tienluong);
			luongRepository.save(entity);
			return LuongConverter.toDTO(entity);
		}).collect(Collectors.toList());
		result.setDulieu(dsDTO);
		result.setKetqua(StringUtil.THANHCONG);
		result.setNoidung("Đã tính lương");
		return result;
	}

	@Override
	public ResultEntity<List<LuongDTO>> dsTheoThang(String nvId, int thang, int nam) {
		ResultEntity<List<LuongDTO>> result = new ResultEntity<>();
		Optional<NhanVien> nhanvienOptional = nhanvienRepository.timId(nvId, StringUtil.ADMIN, StringUtil.DANGHOATDONG);
		if (nhanvienOptional.isPresent()) {
			List<Luong> luongLS = luongRepository.dsTheoNamThang(nam, thang);
			List<LuongDTO> lsDTO = luongLS.stream().map(LuongConverter::toDTO).collect(Collectors.toList());
			result.setDulieu(lsDTO);
			result.setKetqua(StringUtil.THANHCONG);
			result.setNoidung("OK");
			return result;
		}
		
		result.setKetqua(StringUtil.THATBAI);
		result.setNoidung("Not found");
		return result;
		
	}

	@Override
	public ResultEntity<List<LuongDTO>> dsTheoNhanVien(String nvId, int nam) {
		ResultEntity<List<LuongDTO>> result = new ResultEntity<>();
		Optional<NhanVien> nhanvienOptional = nhanvienRepository.findById(nvId);
		if (nhanvienOptional.isPresent()) {
			List<Luong> luongLS = luongRepository.dsTheoNhanVien(nam, nvId);
			List<LuongDTO> lsDTO = luongLS.stream().map(LuongConverter::toDTO).collect(Collectors.toList());
			result.setDulieu(lsDTO);
			result.setKetqua(StringUtil.THANHCONG);
			result.setNoidung("OK");
			return result;
		}
		
		result.setKetqua(StringUtil.THATBAI);
		result.setNoidung("Not found");
		return result;
	}

}
