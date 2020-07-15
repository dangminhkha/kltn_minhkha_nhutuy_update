package com.example.demo.services.impls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.converters.ComboConverter;
import com.example.demo.converters.LoaiMonConverter;
import com.example.demo.converters.MonConverter;
import com.example.demo.dtos.ComboDTO;
import com.example.demo.dtos.LoaiMonDTO;
import com.example.demo.dtos.MonDTO;
import com.example.demo.entitys.Combo;
import com.example.demo.entitys.LoaiMon;
import com.example.demo.entitys.Mon;
import com.example.demo.entitys.NhanVien;
import com.example.demo.entitys.ResultEntity;
import com.example.demo.factorys.LoaiMonFactory;
import com.example.demo.repositorys.LoaiMonRepository;
import com.example.demo.repositorys.NhanVienRepository;
import com.example.demo.services.LoaiMonService;
import com.example.demo.utils.RandomStringExmple;
import com.example.demo.utils.StringUtil;

@Service
public class LoaiMonServiceImpl implements LoaiMonService{
	@Autowired
	private LoaiMonRepository loaiMonRepository;
	@Autowired
	private NhanVienRepository nhanVienRepository;
	@Override
	public ResultEntity<LoaiMonDTO> themLoai(String id, LoaiMonDTO dto) {
		ResultEntity<LoaiMonDTO> result = new ResultEntity<>();
		Optional<NhanVien> nvOptional = nhanVienRepository.findById(id);
		if(nvOptional.isPresent()) {
			Optional<LoaiMon> loaiMonOptional = loaiMonRepository.timTenLoai(dto.getTen(), StringUtil.DANGHOATDONG);
			if(!loaiMonOptional.isPresent()) {
				String idloai = RandomStringExmple.id(6);
				LoaiMon lm = LoaiMonFactory.getInstance();
				lm.setId(idloai);
				lm.setTen(dto.getTen());
				if(dto.getLoai().equalsIgnoreCase("Thức Ăn")) {
					lm.setLoai(StringUtil.DOAN);
				}else{
					lm.setLoai(StringUtil.NUOC);
				}
				loaiMonRepository.save(lm);
				result.setDulieu(dto);
				result.setKetqua(StringUtil.THANHCONG);
				result.setNoidung("Thêm thành công!");
				return result;
			}
			result.setDulieu(dto);
			result.setKetqua(StringUtil.THATBAI);
			result.setNoidung("Tên đã tồn tại!");
			return result;
		}
		
		result.setDulieu(dto);
		result.setKetqua(StringUtil.THATBAI);
		result.setNoidung("Thêm thất bại!");
		return result;
	}
	@Override
	public ResultEntity<List<LoaiMonDTO>> loaiMonAll() {
		ResultEntity<List<LoaiMonDTO>> result = new ResultEntity<>();
		List<LoaiMon> lsLM = loaiMonRepository.loaiMonAll(StringUtil.DANGHOATDONG);
		List<LoaiMonDTO> lsDTO = new ArrayList<LoaiMonDTO>();
		if(!lsLM.isEmpty() && lsLM != null) {
			lsDTO = lsLM.stream().map(LoaiMonConverter::toDTO).collect(Collectors.toList());
			result.setDulieu(lsDTO);
			result.setKetqua(StringUtil.THANHCONG);
			result.setNoidung("Lấy danh sách thành công!");
			return result;
		}
		result.setDulieu(lsDTO);
		result.setKetqua(StringUtil.THATBAI);
		result.setNoidung("Lấy danh sách thất bại!");
		return result;
		
	}
	@Override
	public ResultEntity<List<LoaiMonDTO>> pageLoaiMon(String nvId, int page, int size) {
		ResultEntity<List<LoaiMonDTO>> result = new ResultEntity<>();
		Pageable pageable = PageRequest.of(page, size);
		Page<LoaiMon> dsPage = loaiMonRepository.pageLoaiMon(StringUtil.DANGHOATDONG, pageable);
		List<LoaiMonDTO> dsDTO = new ArrayList<>();
		if(dsPage!=null) {
			dsDTO = dsPage.stream().map(LoaiMonConverter::toDTO).collect(Collectors.toList());
			result.setDulieu(dsDTO);
			result.setKetqua(StringUtil.THANHCONG);
			result.setNoidung("Lấy danh sách thành công!");
			return result;
		}
		result.setDulieu(dsDTO);
		result.setKetqua(StringUtil.THATBAI);
		result.setNoidung("Lấy danh sách thất bại!");
		return result;
	}
	@Override
	public int slPage(String trangthai) {
		int slloai = loaiMonRepository.slloai(trangthai);
		int slPage = slloai/15;
		if(slloai%15>0) {
			slPage++;
		}
		return slPage;
	}
	@Override
	@Transactional
	public ResultEntity<LoaiMonDTO> xoa(String id, String nvId) {
		ResultEntity<LoaiMonDTO> result = new ResultEntity<>();
		Optional<NhanVien> nvOptional = nhanVienRepository.findById(nvId);
		Optional<LoaiMon> loaiOptional = loaiMonRepository.findById(id);
		if (nvOptional.isPresent()) {			
			loaiOptional.get().setTrangthai(StringUtil.DAXOA);
			result.setDulieu(LoaiMonConverter.toDTO(loaiOptional.get()));
			result.setKetqua(StringUtil.THANHCONG);
			result.setNoidung("OK!");
			return result;
		}
		result.setKetqua(StringUtil.THATBAI);
		result.setNoidung("Xóa thất bại");
		return result;
	}
	@Override
	public ResultEntity<List<LoaiMonDTO>> loaiMonAns(String trangthai, String loai) {
		ResultEntity<List<LoaiMonDTO>> result = new ResultEntity<>();
		List<LoaiMon> lsLM = loaiMonRepository.loaiMonAns(trangthai, loai);
		List<LoaiMonDTO> lsDTO = new ArrayList<LoaiMonDTO>();
		if(!lsLM.isEmpty() && lsLM != null) {
			lsDTO = lsLM.stream().map(LoaiMonConverter::toDTO).collect(Collectors.toList());
			result.setDulieu(lsDTO);
			result.setKetqua(StringUtil.THANHCONG);
			result.setNoidung("Lấy danh sách thành công!");
			return result;
		}
		result.setDulieu(lsDTO);
		result.setKetqua(StringUtil.THATBAI);
		result.setNoidung("Lấy danh sách thất bại!");
		return result;
	}

}
