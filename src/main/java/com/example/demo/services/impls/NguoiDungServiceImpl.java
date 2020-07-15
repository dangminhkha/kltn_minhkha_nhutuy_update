package com.example.demo.services.impls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.converters.NguoiDungConverter;
import com.example.demo.converters.NhanVienConverter;
import com.example.demo.dtos.NguoiDungDTO;
import com.example.demo.dtos.NhanVienDTO;
import com.example.demo.entitys.NguoiDung;
import com.example.demo.entitys.NhanVien;
import com.example.demo.entitys.ResultEntity;
import com.example.demo.factorys.NguoiDungFactory;
import com.example.demo.repositorys.NguoiDungRepository;
import com.example.demo.repositorys.NhanVienRepository;
import com.example.demo.services.NguoiDungService;
import com.example.demo.utils.DateUI;
import com.example.demo.utils.MD5;
import com.example.demo.utils.RandomStringExmple;
import com.example.demo.utils.StringUtil;

@Service
public class NguoiDungServiceImpl implements NguoiDungService {
	@Autowired
	private NguoiDungRepository nguoidungRepository;
	@Autowired
	private NhanVienRepository nhanVienRepository;
	@Autowired
	private EntityManager em;

	@Override
	public ResultEntity<NguoiDungDTO> dangKy(NguoiDungDTO dto) {
		ResultEntity<NguoiDungDTO> result = new ResultEntity<>();
		Optional<NguoiDung> userOptional = nguoidungRepository.timSDT(dto.getSodienthoai());
		if (userOptional.isPresent()) {
			result.setDulieu(dto);
			result.setKetqua(StringUtil.THATBAI);
			result.setNoidung("Số điện thoại đã tồn tại");
			return result;
		}
		Optional<NguoiDung> user1Optional = nguoidungRepository.timEmail(dto.getEmail());
		if (user1Optional.isPresent()) {
			result.setDulieu(dto);
			result.setKetqua(StringUtil.THATBAI);
			result.setNoidung("Email đã tồn tại");
			return result;
		}
		NguoiDung kh = NguoiDungFactory.getInstance();
		Date ngaysinh = DateUI.formatDateUI(dto.getNgaysinh(), StringUtil.MOI);
		String id = RandomStringExmple.id(6);
		String mk = MD5.maHoaMatKhau(dto.getMatkhau());
		kh.setId(id);
		kh.setTen(dto.getTen());
		kh.setGioitinh(dto.getGioitinh());
		kh.setNgaysinh(ngaysinh);
		kh.setNoisinh(dto.getNoisinh());
		kh.setSodienthoai(dto.getSodienthoai());
		kh.setSocmnd(dto.getSocmnd());
		kh.setEmail(dto.getEmail());
		kh.setMatkhau(mk);
		nguoidungRepository.save(kh);
		result.setDulieu(NguoiDungConverter.toDTO(kh));
		result.setKetqua(StringUtil.THANHCONG);
		result.setNoidung("Đăng ký thành công");
		return result;

	}

	@Override
	@Transactional
	public ResultEntity<NguoiDungDTO> luucapnhat(NguoiDungDTO dto, String id) {
		ResultEntity<NguoiDungDTO> result = new ResultEntity<>();
		Optional<NguoiDung> nguoidungOptional = nguoidungRepository.timId(id, StringUtil.DANGHOATDONG);
		if (nguoidungOptional.isPresent()) {
			if (!dto.getSodienthoai().equals(nguoidungOptional.get().getSodienthoai())) {
				Optional<NguoiDung> userOptional = nguoidungRepository.timSDT(dto.getSodienthoai());
				if (userOptional.isPresent()) {
					result.setDulieu(dto);
					result.setKetqua(StringUtil.THATBAI);
					result.setNoidung("Số điện thoại đã tồn tại");
					return result;
				}
			}
			if (!dto.getEmail().equals(nguoidungOptional.get().getEmail())) {
				Optional<NguoiDung> userOptional = nguoidungRepository.timEmail(dto.getEmail());
				if (userOptional.isPresent()) {
					result.setDulieu(dto);
					result.setKetqua(StringUtil.THATBAI);
					result.setNoidung("Email đã tồn tại");
					return result;
				}
			}
			Date ngaysinh = DateUI.formatDateUI(dto.getNgaysinh(), StringUtil.CU);
			NguoiDung entity = nguoidungOptional.get();
			entity.setTen(dto.getTen());
			entity.setSodienthoai(dto.getSodienthoai());
			entity.setEmail(dto.getEmail());
			entity.setNgaysinh(ngaysinh);
			entity.setNoisinh(dto.getNoisinh());
			entity.setSocmnd(dto.getSocmnd());
			entity.setNgaycapnhat(new Date());
			NguoiDungDTO nguoiDungDto = NguoiDungConverter.toDTO(entity);
			result.setDulieu(nguoiDungDto);
			result.setKetqua(StringUtil.THANHCONG);
			result.setNoidung("Cập nhật thành công");
			return result;

		}
		result.setDulieu(dto);
		result.setKetqua(StringUtil.THATBAI);
		result.setNoidung("Cập nhật thất bại");
		return result;
	}

	@Override
	@Transactional
	public ResultEntity<NguoiDungDTO> doiMatKhau(String id, String mk, String newMK) {
		ResultEntity<NguoiDungDTO> result = new ResultEntity<>();
		String pass = MD5.maHoaMatKhau(mk);
		Optional<NguoiDung> nguoidungOptional = nguoidungRepository.timIdMk(id, StringUtil.DANGHOATDONG, pass);
		if (nguoidungOptional.isPresent()) {
			String newpass = MD5.maHoaMatKhau(newMK);
			nguoidungOptional.get().setMatkhau(newpass);
			NguoiDungDTO dto = NguoiDungConverter.toDTO(nguoidungOptional.get());
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
	public ResultEntity<List<NguoiDungDTO>> dsKH(String id, int page, int size) {
		ResultEntity<List<NguoiDungDTO>> result = new ResultEntity<>();
		Optional<NhanVien> nvOptional = nhanVienRepository.timId(id, StringUtil.ADMIN, StringUtil.DANGHOATDONG);
		if (nvOptional.isPresent()) {
			Pageable pageable = PageRequest.of(page, size);
			Page<NguoiDung> khPage = nguoidungRepository.dsKH(StringUtil.DANGHOATDONG, pageable);
			List<NguoiDungDTO> dsKHDto = khPage.stream().map(NguoiDungConverter::toDTO).collect(Collectors.toList());
			result.setDulieu(dsKHDto);
			result.setKetqua(StringUtil.THANHCONG);
			result.setNoidung("Lấy danh sách thành công");
			return result;
		}
		result.setKetqua(StringUtil.THATBAI);
		result.setNoidung("Bạn cần đăng nhâp tài khoản quản lý");
		return result;
	}

	@Override
	public int slPage(String trangthai) {
		int slNguoiDung = nguoidungRepository.slNguoiDung(trangthai);
		int slPage = slNguoiDung / 15;
		if (slNguoiDung % 15 > 0) {
			slPage++;
		}
		return slPage;
	}

	@Override
	public ResultEntity<List<NguoiDungDTO>> tim(String keyword) {
		ResultEntity<List<NguoiDungDTO>> result = new ResultEntity<>();
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
		try {
			fullTextEntityManager.createIndexer().startAndWait();
		} catch (InterruptedException e) {
			result.setDulieu(new ArrayList<NguoiDungDTO>());
			return result;
		}
		QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder()
				.forEntity(NguoiDung.class).get();

		org.apache.lucene.search.Query luceneQuery = queryBuilder.keyword().fuzzy().withEditDistanceUpTo(1)
				.withPrefixLength(1).onFields("ten", "sodienthoai", "email").boostedTo(5f).matching(keyword)
				.createQuery();

		FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, NguoiDung.class);
		List<NguoiDung> ds = jpaQuery.getResultList();

		List<NguoiDungDTO> dsDTO = ds.stream().filter(x -> x.getTrangthai().equalsIgnoreCase(StringUtil.DANGHOATDONG))
				.map(NguoiDungConverter::toDTO).collect(Collectors.toList());
		result.setDulieu(dsDTO);
		return result;
	}

	@Override
	public ResultEntity<NguoiDungDTO> timId(String id) {
		ResultEntity<NguoiDungDTO> resutl = new ResultEntity<>();
		Optional<NguoiDung> nguoidungOptional = nguoidungRepository.findById(id);
		if (nguoidungOptional.isPresent()) {
			resutl.setDulieu(NguoiDungConverter.toDTO(nguoidungOptional.get()));
			resutl.setKetqua(StringUtil.THANHCONG);
			resutl.setNoidung("OK");
			return resutl;
		}
		resutl.setKetqua(StringUtil.THATBAI);
		resutl.setNoidung("not found");
		return resutl;
	}

}
