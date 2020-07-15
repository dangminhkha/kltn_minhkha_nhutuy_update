package com.example.demo.services.impls;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.converters.NguoiDungConverter;
import com.example.demo.converters.NhanVienConverter;
import com.example.demo.converters.SanhConverter;
import com.example.demo.dtos.NguoiDungDTO;
import com.example.demo.dtos.NhanVienDTO;
import com.example.demo.dtos.SanhDTO;
import com.example.demo.entitys.BangCong;
import com.example.demo.entitys.NguoiDung;
import com.example.demo.entitys.NhanVien;
import com.example.demo.entitys.ResultEntity;
import com.example.demo.entitys.Sanh;
import com.example.demo.factorys.NguoiDungFactory;
import com.example.demo.factorys.NhanVienFactory;
import com.example.demo.repositorys.BangCongRepository;
import com.example.demo.repositorys.NhanVienRepository;
import com.example.demo.services.NhanVienService;
import com.example.demo.utils.DateUI;
import com.example.demo.utils.MD5;
import com.example.demo.utils.RandomStringExmple;
import com.example.demo.utils.StringUtil;
import com.ibm.icu.text.SimpleDateFormat;

@Service
public class NhanVienServiceImpl implements NhanVienService {
	@Autowired
	private NhanVienRepository nhanVienRepository;
	@Autowired
	private EntityManager em;
	@Autowired
	private BangCongRepository bangCongRepository;

	@Override
	public ResultEntity<List<NhanVienDTO>> dsNhanVien(String id, String chucvu, int page, int size) {
		ResultEntity<List<NhanVienDTO>> result = new ResultEntity<>();
		Optional<NhanVien> nvOptional = nhanVienRepository.findById(id);
		List<NhanVienDTO> dsNvDTO = new ArrayList<NhanVienDTO>();
		if (nvOptional.isPresent()) {
			Pageable pageable = PageRequest.of(page, size);
			Page<NhanVien> nhanvienPage = nhanVienRepository.dsNhanVien(StringUtil.DANGHOATDONG, chucvu, pageable);
			dsNvDTO = nhanvienPage.stream().filter(x -> !x.getChucvu().equals(StringUtil.ADMIN))
					.map(NhanVienConverter::toDTO).collect(Collectors.toList());
			result.setDulieu(dsNvDTO);
			result.setKetqua(StringUtil.THANHCONG);
			result.setNoidung("Lấy danh sách thành công!");
			return result;
		}
		result.setDulieu(dsNvDTO);
		result.setKetqua(StringUtil.THATBAI);
		result.setNoidung("Lấy danh thất bại!");
		return result;
	}

	@Override
	public ResultEntity<NhanVienDTO> themNV(NhanVienDTO dto, String id) {
		ResultEntity<NhanVienDTO> result = new ResultEntity<>();
		Optional<NhanVien> nvOptional = nhanVienRepository.findById(id);
		if (nvOptional.isPresent()) {

			Optional<NhanVien> nvSdtOptional = nhanVienRepository.timSdt(dto.getSodienthoai());
			if (nvSdtOptional.isPresent()) {
				result.setKetqua(StringUtil.THATBAI);
				result.setNoidung("Số điện thoại đã tồn tại!");
				return result;
			}

			Optional<NhanVien> nvEmailOptional = nhanVienRepository.timEmail(dto.getEmail());
			if (nvEmailOptional.isPresent()) {
				result.setKetqua(StringUtil.THATBAI);
				result.setNoidung("Email đã tồn tại!");
				return result;
			}

			NhanVien nv = NhanVienFactory.getInstance();
			Date ngaysinh = DateUI.formatDateUI(dto.getNgaysinh(), StringUtil.MOI);
			String idNV = RandomStringExmple.id(6);
			nv.setId(idNV);
			nv.setTen(dto.getTen());
			nv.setGioitinh(dto.getGioitinh());
			nv.setNgaysinh(ngaysinh);
			nv.setNoisinh(dto.getNoisinh());
			nv.setSodienthoai(dto.getSodienthoai());
			nv.setSocmnd(dto.getSocmnd());
			nv.setEmail(dto.getEmail());
			if (null != dto.getMatkhau() && !dto.getMatkhau().isEmpty()) {
				nv.setMatkhau(MD5.maHoaMatKhau(dto.getMatkhau()));
			}
			nv.setChucvu(dto.getChucvu());
			nv.setLuong(dto.getLuong());
			nhanVienRepository.save(nv);
			result.setDulieu(dto);
			result.setKetqua(StringUtil.THANHCONG);
			result.setNoidung("thêm thành công!");
			return result;

		}
		result.setDulieu(dto);
		result.setKetqua(StringUtil.THATBAI);
		result.setNoidung("Bạn cần đăng nhập tài khoản quản lý!");
		return result;
	}

	@Override
	public int slPage(String trangthai, String chucvu) {
		int slnhanvien = nhanVienRepository.slNhanVien(trangthai, chucvu);
		int slPage = slnhanvien / 15;
		if (slnhanvien % 15 > 0) {
			slPage++;
		}
		return slPage;
	}

	@Override
	@Transactional
	public ResultEntity<NhanVienDTO> xoa(String adminId, String id) {
		ResultEntity<NhanVienDTO> result = new ResultEntity<>();
		Optional<NhanVien> adminOptional = nhanVienRepository.timId(adminId, StringUtil.ADMIN, StringUtil.DANGHOATDONG);
		if (adminOptional.isPresent()) {
			Optional<NhanVien> nhanvienOptional = nhanVienRepository.findById(id);
			nhanvienOptional.get().setTrangthai(StringUtil.DAXOA);
			result.setDulieu(NhanVienConverter.toDTO(nhanvienOptional.get()));
			result.setKetqua(StringUtil.THANHCONG);
			result.setNoidung("OK");
			return result;
		}
		result.setKetqua(StringUtil.THATBAI);
		result.setNoidung("Bạn cần đăng nhập tài khoản admin");
		return result;
	}

	@Override
	@Transactional
	public ResultEntity<NhanVienDTO> doiMatKhau(String id, String mk, String newMK) {
		ResultEntity<NhanVienDTO> result = new ResultEntity<>();
		String pass = MD5.maHoaMatKhau(mk);
		Optional<NhanVien> nguoidungOptional = nhanVienRepository.timIdMk(id, StringUtil.DANGHOATDONG, pass);
		if (nguoidungOptional.isPresent()) {
			String newpass = MD5.maHoaMatKhau(newMK);
			nguoidungOptional.get().setMatkhau(newpass);
			NhanVienDTO dto = NhanVienConverter.toDTO(nguoidungOptional.get());
			result.setDulieu(dto);
			result.setKetqua(StringUtil.THANHCONG);
			result.setNoidung("Cập nhật thành công");
			return result;
		}
		result.setKetqua(StringUtil.THATBAI);
		result.setNoidung("Cập nhật thất bại");
		return result;
	}

	@Override
	public ResultEntity<List<NhanVienDTO>> tim(String keyword, String chucvu) {
		ResultEntity<List<NhanVienDTO>> result = new ResultEntity<>();
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
		try {
			fullTextEntityManager.createIndexer().startAndWait();
		} catch (InterruptedException e) {
			result.setDulieu(new ArrayList<NhanVienDTO>());
			return result;
		}
		QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder()
				.forEntity(NhanVien.class).get();

		org.apache.lucene.search.Query luceneQuery = queryBuilder.keyword().fuzzy().withEditDistanceUpTo(1)
				.withPrefixLength(1).onFields("ten", "sodienthoai", "email").boostedTo(5f).matching(keyword)
				.createQuery();

		FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, NhanVien.class);
		List<NhanVien> ds = jpaQuery.getResultList();

		List<NhanVienDTO> dsDTO = ds.stream()
				.filter(x -> x.getTrangthai().equalsIgnoreCase(StringUtil.DANGHOATDONG) && x.getChucvu().equals(chucvu))
				.map(NhanVienConverter::toDTO).collect(Collectors.toList());
		result.setDulieu(dsDTO);
		return result;
	}

	@Override
	public ResultEntity<List<NhanVienDTO>> dsAll(String trangthai, String nvId, int nam, int thang) {
		ResultEntity<List<NhanVienDTO>> result = new ResultEntity<>();
		Optional<NhanVien> nvOptional = nhanVienRepository.timId(nvId, StringUtil.ADMIN, StringUtil.DANGHOATDONG);
		List<NhanVienDTO> dsNvDTO = new ArrayList<NhanVienDTO>();
		if (nvOptional.isPresent()) {

			thang--;
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.set(Calendar.YEAR, nam);
			calendar.set(Calendar.MONTH, thang);
			calendar.set(Calendar.DATE, 1);
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			Date startDay = calendar.getTime();
			if (thang == 12) {
				calendar.set(Calendar.YEAR, nam + 1);
				calendar.set(Calendar.MONDAY, 1);
			} else {
				calendar.set(Calendar.MONDAY, thang + 1);
			}
			Date endDay = calendar.getTime();

			List<NhanVien> nhanvienList = nhanVienRepository.dsAll(trangthai, StringUtil.NHANVIEN);
			dsNvDTO = nhanvienList.stream().filter(x -> !x.getChucvu().equals(StringUtil.ADMIN)).map(x -> {
				NhanVienDTO dto = NhanVienConverter.toDTO(x);
				List<BangCong> dsCongNV = bangCongRepository.dsCongNhanVien(dto.getId(), startDay, endDay);
				dto.setTongcongthang(dsCongNV.size());
				return dto;

			}).collect(Collectors.toList());
			result.setDulieu(dsNvDTO);
			result.setKetqua(StringUtil.THANHCONG);
			result.setNoidung("Lấy danh sách thành công!");
			return result;
		}
		result.setDulieu(dsNvDTO);
		result.setKetqua(StringUtil.THATBAI);
		result.setNoidung("Lấy danh thất bại!");
		return result;
	}

	@Override
	public ResultEntity<List<NhanVienDTO>> dsCongTheoNgay(Date ngaycong, String trangthai, String loainhanvien) {
		ResultEntity<List<NhanVienDTO>> result = new ResultEntity<>();
		List<NhanVien> dsNhanVienChamCong = nhanVienRepository.dsNhanVienChamCong(trangthai, loainhanvien);

		List<NhanVienDTO> dsDTO = dsNhanVienChamCong.stream().map(x -> {

			AtomicBoolean b = new AtomicBoolean(false);
			x.getBangCongs().stream().map(z -> {
				SimpleDateFormat datefm = new SimpleDateFormat("dd-MM-yyyy");
				if (datefm.format(ngaycong).equals(datefm.format(z.getNgaycong()))) {
					b.set(true);
				}

				return z;
			}).collect(Collectors.toList());
			NhanVienDTO dto = NhanVienConverter.toDTO(x);
			if (b.get() == true) {
				dto.setDachamcong(true);
			} else {
				dto.setDachamcong(false);
			}
			return dto;
		}).collect(Collectors.toList());

		result.setDulieu(dsDTO);
		result.setKetqua(StringUtil.THANHCONG);
		result.setNoidung("OK");
		return result;
	}

	@Override
	public ResultEntity<NhanVienDTO> nhanvienId(String id) {
		ResultEntity<NhanVienDTO> result = new ResultEntity<>();
		Optional<NhanVien> nhanvienOptional = nhanVienRepository.findById(id);
		if (nhanvienOptional.isPresent()) {
			result.setDulieu(NhanVienConverter.toDTO(nhanvienOptional.get()));
			result.setKetqua(StringUtil.THANHCONG);
			return result;
		}
		result.setKetqua(StringUtil.THATBAI);
		return result;
	}

	@Override
	public ResultEntity<List<NhanVienDTO>> dsNhanVienTheoNgay(Date ngaycong, String trangthai, String chucvu) {
		ResultEntity<List<NhanVienDTO>> result = new ResultEntity<>();
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
		List<NhanVien> dsNhanVienChamCong = nhanVienRepository.dsNhanVienTheoNgay(trangthai, chucvu, startDay, endDay);
		List<NhanVienDTO> dsDTO = dsNhanVienChamCong.stream().map(NhanVienConverter::toDTO)
				.collect(Collectors.toList());
		result.setDulieu(dsDTO);
		result.setKetqua(StringUtil.THANHCONG);
		result.setNoidung("OK");
		return result;
	}

	@Override
	@Transactional
	public ResultEntity<NhanVienDTO> capNhat(NhanVienDTO dto) {
		ResultEntity<NhanVienDTO> result = new ResultEntity<>();
		Optional<NhanVien> nvOptional = nhanVienRepository.findById(dto.getId());
		if (nvOptional.isPresent()) {
			if (!dto.getSodienthoai().equals(nvOptional.get().getSodienthoai())) {
				Optional<NhanVien> nvSdtOptional = nhanVienRepository.timSdt(dto.getSodienthoai());
				if (nvSdtOptional.isPresent()) {
					result.setKetqua(StringUtil.THATBAI);
					result.setNoidung("Số điện thoại đã tồn tại!");
					return result;
				}

			}
			if(!dto.getEmail().equals(nvOptional.get().getEmail())) {
				Optional<NhanVien> nvEmailOptional = nhanVienRepository.timEmail(dto.getEmail());
				if (nvEmailOptional.isPresent()) {
					result.setKetqua(StringUtil.THATBAI);
					result.setNoidung("Email đã tồn tại!");
					return result;
				}
	
			}
			
			Date ngaysinh = DateUI.formatDateUI(dto.getNgaysinh(), StringUtil.CU);
			nvOptional.get().setTen(dto.getTen());
			nvOptional.get().setGioitinh(dto.getGioitinh());
			nvOptional.get().setNgaysinh(ngaysinh);
			nvOptional.get().setNoisinh(dto.getNoisinh());
			nvOptional.get().setSodienthoai(dto.getSodienthoai());
			nvOptional.get().setSocmnd(dto.getSocmnd());
			nvOptional.get().setEmail(dto.getEmail());
			if(dto.getLuong()!=0) {
				nvOptional.get().setLuong(dto.getLuong());
			}
			result.setDulieu(NhanVienConverter.toDTO(nvOptional.get()));
			result.setKetqua(StringUtil.THANHCONG);
			result.setNoidung("thêm thành công!");
			return result;

		}
		result.setDulieu(dto);
		result.setKetqua(StringUtil.THATBAI);
		result.setNoidung("Bạn cần đăng nhập tài khoản quản lý!");
		return result;
	}

}
