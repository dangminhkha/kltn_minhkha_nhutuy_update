package com.example.demo.services.impls;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.converters.HoaDonConverter;
import com.example.demo.converters.TTDatTiecConverter;
import com.example.demo.dtos.HoaDonDTO;
import com.example.demo.dtos.TTDatTiecDTO;
import com.example.demo.entitys.CTHoaDon;
import com.example.demo.entitys.Combo;
import com.example.demo.entitys.HoaDon;
import com.example.demo.entitys.Mon;
import com.example.demo.entitys.NhanVien;
import com.example.demo.entitys.ResultEntity;
import com.example.demo.entitys.TTDatTiec;
import com.example.demo.factorys.CTHoaDonFactory;
import com.example.demo.factorys.HoaDonFactory;
import com.example.demo.repositorys.ComboRepository;
import com.example.demo.repositorys.HoaDonRepository;
import com.example.demo.repositorys.MonRepository;
import com.example.demo.repositorys.NhanVienRepository;
import com.example.demo.repositorys.TTDatTiecRepository;
import com.example.demo.services.HoaDonService;
import com.example.demo.utils.StringUtil;

@Service
public class HoaDonServiceImpl implements HoaDonService{
	@Autowired
	private MonRepository monRepository;
	@Autowired
	private ComboRepository comboRepository;
	@Autowired
	private TTDatTiecRepository ttdattiecRepository;
	@Autowired
	private NhanVienRepository nhanVienRepository;	
	@Autowired
	private HoaDonRepository hoaDonRepository;
	@Override
	public ResultEntity<HoaDonDTO> formatHoaDon(HoaDonDTO dto,String nvId) {
		ResultEntity<HoaDonDTO> result = new ResultEntity<>();
		Optional<NhanVien> nvOptional = nhanVienRepository.findById(nvId);
		if (nvOptional.isPresent()) {
			HoaDon entity = HoaDonFactory.getInstance();
			Optional<TTDatTiec> ttdtOptional = ttdattiecRepository.findById(dto.getTtDatTiec().getId());
			entity.setTtDatTiec(ttdtOptional.get());
			entity.setGia(0);
			
			dto.getCtHoaDons().stream()
			.filter(x->x.getMon()!=null)
			.map(x->{
				Optional<Mon> monOptional = monRepository.findById(x.getMon().getId());
				CTHoaDon cthoadon = CTHoaDonFactory.getInstance();
				cthoadon.setSoluong(x.getSoluong());
				cthoadon.setMon(monOptional.get());
				cthoadon.setGia(monOptional.get().getGia()*x.getSoluong());
				cthoadon.setHoaDon(entity);
				entity.setGia(entity.getGia()+cthoadon.getGia());
				entity.getCtHoaDons().add(cthoadon);
				return x;
			}).collect(Collectors.toList());
			
			dto.getCtHoaDons().stream()
			.filter(x->x.getCombo()!=null)
			.map(x->{
				Optional<Combo> comOptional = comboRepository.findById(x.getCombo().getId());
				CTHoaDon cthoadon = CTHoaDonFactory.getInstance();
				cthoadon.setSoluong(x.getSoluong());
				cthoadon.setCombo(comOptional.get());
				cthoadon.setGia(comOptional.get().getGia()*x.getSoluong());
				cthoadon.setHoaDon(entity);
				entity.setGia(entity.getGia()+cthoadon.getGia());
				entity.getCtHoaDons().add(cthoadon);
				return x;
			}).collect(Collectors.toList());
			
			dto.getCtHoaDons().stream()
			.filter(x->x.getTen()!=null)
			.map(x->{
				CTHoaDon cthoadon = CTHoaDonFactory.getInstance();
				cthoadon.setSoluong(x.getSoluong());
				cthoadon.setTen(x.getTen());
				cthoadon.setGia(x.getGia());
				cthoadon.setHoaDon(entity);
				entity.setGia(entity.getGia()+cthoadon.getGia());
				entity.getCtHoaDons().add(cthoadon);
				return x;
			}).collect(Collectors.toList());
			
			
			result.setDulieu(HoaDonConverter.toDTO(entity));
			return result;
		}
		
		return null;
	}

	@Override
	@Transactional
	public ResultEntity<HoaDonDTO> thanhtoan(HoaDonDTO dto, String nvId) {
		ResultEntity<HoaDonDTO> result = new ResultEntity<>();
		Optional<NhanVien> nvOptional = nhanVienRepository.findById(nvId);
		if (nvOptional.isPresent()) {
			HoaDon entity = HoaDonFactory.getInstance();
			Optional<TTDatTiec> ttdtOptional = ttdattiecRepository.timTheoTT(dto.getTtDatTiec().getId(), StringUtil.DADUYET);
			if (ttdtOptional.isPresent()) {
				entity.setTtDatTiec(ttdtOptional.get());
				entity.setGia(0);
				dto.getCtHoaDons().stream()
				.filter(x->x.getMon()!=null)
				.map(x->{
					Optional<Mon> monOptional = monRepository.findById(x.getMon().getId());
					CTHoaDon cthoadon = CTHoaDonFactory.getInstance();
					cthoadon.setSoluong(x.getSoluong());
					cthoadon.setMon(monOptional.get());
					cthoadon.setGia(monOptional.get().getGia()*x.getSoluong());
					cthoadon.setHoaDon(entity);
					entity.setGia(entity.getGia()+cthoadon.getGia());
					entity.getCtHoaDons().add(cthoadon);
					return x;
				}).collect(Collectors.toList());
				dto.getCtHoaDons().stream()
				.filter(x->x.getCombo()!=null)
				.map(x->{
					Optional<Combo> comOptional = comboRepository.findById(x.getCombo().getId());
					CTHoaDon cthoadon = CTHoaDonFactory.getInstance();
					cthoadon.setSoluong(x.getSoluong());
					cthoadon.setCombo(comOptional.get());
					cthoadon.setGia(comOptional.get().getGia()*x.getSoluong());
					cthoadon.setHoaDon(entity);
					entity.setGia(entity.getGia()+cthoadon.getGia());
					entity.getCtHoaDons().add(cthoadon);
					return x;
				}).collect(Collectors.toList());
				dto.getCtHoaDons().stream()
				.filter(x->x.getTen()!=null)
				.map(x->{
					CTHoaDon cthoadon = CTHoaDonFactory.getInstance();
					cthoadon.setSoluong(x.getSoluong());
					cthoadon.setTen(x.getTen());
					cthoadon.setGia(x.getGia());
					cthoadon.setHoaDon(entity);
					entity.setGia(entity.getGia()+cthoadon.getGia());
					entity.getCtHoaDons().add(cthoadon);
					return x;
				}).collect(Collectors.toList());
				entity.setNhanvien(nvOptional.get());
				ttdtOptional.get().setTrangthai(StringUtil.DATHANHTOAN);
				hoaDonRepository.save(entity);
				result.setKetqua(StringUtil.THANHCONG);
				result.setDulieu(HoaDonConverter.toDTO(entity));
				result.setNoidung("OK");
				return result;	
			}
			result.setKetqua(StringUtil.THATBAI);
			result.setNoidung("Thông tin đặt tiệc có thể đã được thanh toán!");
			return result;
		}
		result.setKetqua(StringUtil.THATBAI);
		result.setNoidung("Thất Bại");
		return result;
	}

	@Override
	public ResultEntity<HoaDonDTO> timid(String id, String idnd, String loai) {
		ResultEntity<HoaDonDTO> result = new ResultEntity<>();
		Optional<HoaDon> hoadonOptional;
		if (loai.equalsIgnoreCase(StringUtil.KHACHHANG)) {
			hoadonOptional = hoaDonRepository.timId(idnd, id);
		}else {
			hoadonOptional = hoaDonRepository.timHDNV(id);
		}
		if (hoadonOptional.isPresent()) {
			HoaDonDTO dto = HoaDonConverter.toDTO(hoadonOptional.get());
			result.setDulieu(dto);
			result.setKetqua(StringUtil.THANHCONG);
			result.setNoidung("thành công!");
			return result;
		}
		result.setKetqua(StringUtil.THATBAI);
		result.setNoidung("thất bại!");
		return result;
	}

}
