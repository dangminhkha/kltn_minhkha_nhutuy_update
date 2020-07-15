package com.example.demo.services.impls;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.converters.TTDatTiecConverter;
import com.example.demo.dtos.TTDatTiecDTO;
import com.example.demo.entitys.CTCombo;
import com.example.demo.entitys.CTNuocDatTiec;
import com.example.demo.entitys.Combo;
import com.example.demo.entitys.Mon;
import com.example.demo.entitys.NguoiDung;
import com.example.demo.entitys.NhanVien;
import com.example.demo.entitys.ResultEntity;
import com.example.demo.entitys.Sanh;
import com.example.demo.entitys.TTDatTiec;
import com.example.demo.factorys.CTComboFactory;
import com.example.demo.factorys.CTNuocDatTiecFactory;
import com.example.demo.factorys.ComboFactory;
import com.example.demo.factorys.TTDatTiecFactory;
import com.example.demo.repositorys.ComboRepository;
import com.example.demo.repositorys.MonRepository;
import com.example.demo.repositorys.NguoiDungRepository;
import com.example.demo.repositorys.NhanVienRepository;
import com.example.demo.repositorys.SanhRepository;
import com.example.demo.repositorys.TTDatTiecRepository;
import com.example.demo.services.TTDatTiecService;
import com.example.demo.utils.DateUI;
import com.example.demo.utils.RandomStringExmple;
import com.example.demo.utils.StringUtil;
import com.ibm.icu.text.SimpleDateFormat;

@Service
public class TTDatTiecServiceImpl implements TTDatTiecService {

	@Autowired
	private TTDatTiecRepository ttdattiecRepository;
	@Autowired
	private NguoiDungRepository nguoidungRepository;
	@Autowired
	private ComboRepository comboRepository;
	@Autowired
	private SanhRepository sanhRepository;
	@Autowired
	private MonRepository monRepository;
	@Autowired
	private NhanVienRepository nhanvienRepository;

	@Override
	@Transactional
	public ResultEntity<TTDatTiecDTO> dattiec(TTDatTiecDTO dto) {
		ResultEntity<TTDatTiecDTO> result = new ResultEntity<>();
		
		Date d = DateUI.formatDateUI(dto.getNgaytochuc(), StringUtil.CU);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date startDay = calendar.getTime();
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		Date endDay = calendar.getTime();
		
		SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");
		
		System.out.println("startDay: "+sm.format(startDay));
		
		Optional<TTDatTiec> ttOptional = ttdattiecRepository.timTTDTTheoNgay(dto.getSanh().getId(),dto.getCatochuc(), startDay, endDay);
		if (ttOptional.isPresent()) {
			result.setKetqua(StringUtil.THATBAI);
			result.setNoidung("Đã có người đặt sãnh này rồi vui lòng chọn lại sãnh");
			return result;
		}
		
		Optional<NguoiDung> ngdungOptional = nguoidungRepository.timId(dto.getNguoidung().getId(),
				StringUtil.DANGHOATDONG);
		if (ngdungOptional.isPresent()) {
			Optional<Sanh> sanhOptional = sanhRepository.findById(dto.getSanh().getId());
			if (sanhOptional.isPresent()) {
				TTDatTiec entity = TTDatTiecFactory.getInstance();
				
				if (dto.getCombo().getId()==null ||dto.getCombo().getId().length()==0) {
					
					Combo combokh = ComboFactory.getInstance();
					combokh.setId(RandomStringExmple.id(6));
					combokh.setTrangthai(StringUtil.KHACHHANG);
					combokh.setNguoidung(ngdungOptional.get());
					combokh.setGia(dto.getCombo().getGia());
					combokh.setTen("Combo thức ăn");
					dto.getCombo().getCtcombos().stream().map(x -> {
						CTCombo ctcbkh = CTComboFactory.getInstance();
						Optional<Mon> monOptional = monRepository.findById(x.getMon().getId());
						ctcbkh.setMon(monOptional.get());
						ctcbkh.setCombo(combokh);
						ctcbkh.setStt(x.getStt());
						combokh.getCtcombos().add(ctcbkh);
						return x;
					}).collect(Collectors.toList());
					comboRepository.save(combokh);
					
					entity.setCombo(combokh);
					
				}else {
					Optional<Combo> comboOptional = comboRepository.findById(dto.getCombo().getId());
					entity.setCombo(comboOptional.get());
				}
				
				String id = RandomStringExmple.id(6);
				entity.setId(id);
				entity.setNguoidung(ngdungOptional.get());
				entity.setSanh(sanhOptional.get());
				entity.setNgaytochuc(d);
				entity.setCatochuc(dto.getCatochuc());
				entity.setGhichu(dto.getGhichu());
				entity.setTiencoc(dto.getTiencoc());
				entity.setSoban(dto.getSoban());
				entity.setSokhach(dto.getSokhach());
				entity.setLoai(dto.getLoai());
				ngdungOptional.get().setSolandat(ngdungOptional.get().getSolandat() + 1);
				List<CTNuocDatTiec> nuocs = dto.getCtNuocDatTiecs().stream().map(x -> {
					CTNuocDatTiec ncen = CTNuocDatTiecFactory.getInstance();
					Optional<Mon> mncOptional = monRepository.findById(x.getMon().getId());
					System.out.println(mncOptional.get().getId());
					ncen.setMon(mncOptional.get());
					ncen.setTtDatTiec(entity);
					return ncen;
				}).collect(Collectors.toList());
				entity.getCtNuocDatTiecs().addAll(nuocs);
				ttdattiecRepository.save(entity);
				TTDatTiecDTO ttdto = TTDatTiecConverter.toDTO(entity);
				result.setDulieu(ttdto);
				result.setKetqua(StringUtil.THANHCONG);
				result.setNoidung("Đặt tiệc thành công!");
				return result;
			}
		}
		result.setKetqua(StringUtil.THATBAI);
		result.setNoidung("Đặt tiệc thất bại!");
		return result;
	}

	@Override
	public ResultEntity<List<TTDatTiecDTO>> dsTheoIdKh(String id, String trangthai, int page, int size) {
		ResultEntity<List<TTDatTiecDTO>> result = new ResultEntity<>();
		Optional<NguoiDung> ngdungOptional = nguoidungRepository.timId(id, StringUtil.DANGHOATDONG);
		List<TTDatTiecDTO> dsDTO = new ArrayList<>();
		if (ngdungOptional.isPresent()) {
			Pageable pageable = PageRequest.of(page, size);
			Page<TTDatTiec> ttDatTiecPage = ttdattiecRepository.timTheoIdKh(id, trangthai, pageable);
			if (ttDatTiecPage != null) {
				dsDTO = ttDatTiecPage.stream().map(TTDatTiecConverter::toDTO).collect(Collectors.toList());
				result.setDulieu(dsDTO);
				result.setKetqua(StringUtil.THANHCONG);
				result.setNoidung("thành công!");
				return result;
			}
		}
		result.setDulieu(dsDTO);
		result.setKetqua(StringUtil.THATBAI);
		result.setNoidung("thất bại!");
		return result;
	}

	@Override
	public ResultEntity<TTDatTiecDTO> timid(String id, String idnd, String loai) {
		ResultEntity<TTDatTiecDTO> result = new ResultEntity<>();
		Optional<TTDatTiec> ttdtOptional;
		if (loai.equalsIgnoreCase(StringUtil.KHACHHANG)) {
			ttdtOptional = ttdattiecRepository.timId(idnd, id);
		} else {
			ttdtOptional = ttdattiecRepository.findById(id);
		}
		if (ttdtOptional.isPresent()) {
			TTDatTiecDTO dto = TTDatTiecConverter.toDTO(ttdtOptional.get());
			result.setDulieu(dto);
			result.setKetqua(StringUtil.THANHCONG);
			result.setNoidung("thành công!");
			return result;
		}
		result.setKetqua(StringUtil.THATBAI);
		result.setNoidung("thất bại!");
		return result;
	}

	@Override
	@Transactional
	public ResultEntity<TTDatTiecDTO> doiTT(String id, String idnd, String trangthai, String loai) {
		ResultEntity<TTDatTiecDTO> result = new ResultEntity<>();
		Optional<TTDatTiec> ttdtOptional;
		if (loai.equalsIgnoreCase(StringUtil.KHACHHANG)) {
			ttdtOptional = ttdattiecRepository.timId(idnd, id);
		} else {
			ttdtOptional = ttdattiecRepository.findById(id);
		}
		if (ttdtOptional.isPresent()) {
			ttdtOptional.get().setTrangthai(trangthai);
			TTDatTiecDTO dto = TTDatTiecConverter.toDTO(ttdtOptional.get());
			result.setDulieu(dto);
			result.setKetqua(StringUtil.THANHCONG);
			result.setNoidung("thành công!");
			return result;

		}
		result.setKetqua(StringUtil.THATBAI);
		result.setNoidung("thất bại!");
		return result;
	}

	@Override
	public int slPage(String id, String trangthai) {
		int slTTDT = ttdattiecRepository.slTTDT(id, trangthai);
		int slPage = slTTDT / 15;
		if (slTTDT % 15 > 0) {
			slPage++;
		}
		return slPage;
	}

	@Override
	public ResultEntity<List<TTDatTiecDTO>> dsTheoTT(String id, String trangthai, int page, int size) {
		ResultEntity<List<TTDatTiecDTO>> result = new ResultEntity<>();
		Optional<NhanVien> nvOptional = nhanvienRepository.findById(id);
		List<TTDatTiecDTO> dsDTO = new ArrayList<>();
		if (nvOptional.isPresent()) {
			Pageable pageable = PageRequest.of(page, size);
			Page<TTDatTiec> ttDatTiecPage = ttdattiecRepository.dsTheoTT(trangthai, pageable);
			if (ttDatTiecPage != null) {
				dsDTO = ttDatTiecPage.stream().map(TTDatTiecConverter::toDTO).collect(Collectors.toList());
				result.setDulieu(dsDTO);
				result.setKetqua(StringUtil.THANHCONG);
				result.setNoidung("thành công!");
				return result;
			}
		}
		result.setDulieu(dsDTO);
		result.setKetqua(StringUtil.THATBAI);
		result.setNoidung("thất bại!");
		return result;
	}

	@Override
	public int slPageAdmin(String trangthai) {
		int slTTDT = ttdattiecRepository.slTTDTAdmin(trangthai);
		int slPage = slTTDT / 15;
		if (slTTDT % 15 > 0) {
			slPage++;
		}
		return slPage;
	}

	@Override
	@Transactional
	public ResultEntity<TTDatTiecDTO> duyetTTDT(String userId, String id, int tiencoc) {
		ResultEntity<TTDatTiecDTO> result = new ResultEntity<>();
		Optional<NhanVien> nvOptional = nhanvienRepository.findById(userId);
		if (nvOptional.isPresent()) {
			Optional<TTDatTiec> ttdtOptional = ttdattiecRepository.findById(id);
			if (ttdtOptional.isPresent()) {
				ttdtOptional.get().setTiencoc(tiencoc);
				ttdtOptional.get().setTrangthai(StringUtil.DADUYET);
				TTDatTiecDTO dto = TTDatTiecConverter.toDTO(ttdtOptional.get());
				result.setDulieu(dto);
				result.setKetqua(StringUtil.THANHCONG);
				result.setNoidung("thành công!");
				return result;
			}
			result.setKetqua(StringUtil.THATBAI);
			result.setNoidung("Không tìm thấy thông tin đặt tiệc");
			return result;
		}
		result.setKetqua(StringUtil.THATBAI);
		result.setNoidung("Bạn cần phải đăng nhập tài khoản nhân viên");
		return result;
	}

}
