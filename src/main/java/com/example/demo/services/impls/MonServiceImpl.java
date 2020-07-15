package com.example.demo.services.impls;

import java.util.ArrayList;
import java.util.Collection;
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

import com.example.demo.converters.MonConverter;
import com.example.demo.converters.SanhConverter;
import com.example.demo.dtos.MonDTO;
import com.example.demo.dtos.SanhDTO;
import com.example.demo.entitys.LoaiMon;
import com.example.demo.entitys.Mon;
import com.example.demo.entitys.NhanVien;
import com.example.demo.entitys.ResultEntity;
import com.example.demo.entitys.Sanh;
import com.example.demo.factorys.MonFactory;
import com.example.demo.repositorys.LoaiMonRepository;
import com.example.demo.repositorys.MonRepository;
import com.example.demo.repositorys.NhanVienRepository;
import com.example.demo.services.MonService;
import com.example.demo.utils.RandomStringExmple;
import com.example.demo.utils.StringUtil;

@Service
public class MonServiceImpl implements MonService{
	@Autowired
	private NhanVienRepository nhanVienRepository;
	@Autowired
	private MonRepository monRepository;
	@Autowired
	private LoaiMonRepository loaiMonRepository;

	@Autowired
	private EntityManager em;
	@Override
	public ResultEntity<MonDTO> themMon(String id, MonDTO dto) {
		ResultEntity<MonDTO> result = new ResultEntity<>();
		Optional<NhanVien> nvOptional = nhanVienRepository.findById(id);
		if(!nvOptional.isPresent()) {
			result.setDulieu(dto);
			result.setKetqua(StringUtil.THATBAI);
			result.setNoidung("Bạn cần phải đăng nhập bằng tài khoản của nhân viên!");
			return result;
		}
		Optional<Mon> monOptional = monRepository.timTen(dto.getTen(), StringUtil.DANGHOATDONG);
		if(monOptional.isPresent()) {
			result.setDulieu(dto);
			result.setKetqua(StringUtil.THATBAI);
			result.setNoidung("Tên món đã tồn tại!");
			return result;
		}
		Optional<LoaiMon> loaiMonOptional = loaiMonRepository.timTenLoai(dto.getLoai(), StringUtil.DANGHOATDONG);
		if(loaiMonOptional.isPresent()) {
			Mon entity = MonFactory.getInstance();
			String idMon = RandomStringExmple.id(6);
			entity.setId(idMon);
			entity.setGhichu(dto.getGhichu());
			entity.setGia(dto.getGia());
			entity.setTen(dto.getTen());
			entity.setLoaimon(loaiMonOptional.get());
			entity.setNhanvien(nvOptional.get());
			entity.setHinh(dto.getHinh());
			monRepository.save(entity);
			result.setDulieu(dto);
			result.setKetqua(StringUtil.THANHCONG);
			result.setNoidung("Thêm món thành công!");
			return result;
		}
		result.setDulieu(dto);
		result.setKetqua(StringUtil.THATBAI);
		result.setNoidung("Thêm món thất bại!");
		return result;
		
	}

	@Override
	public ResultEntity<List<MonDTO>> dsMonTheoLoai(String idloai,int page,int size) {
		ResultEntity<List<MonDTO>> result = new ResultEntity<>();
		Pageable pageable = PageRequest.of(page, size);
		Page<Mon> dsPage;
		if(idloai.equals("allmon")) {
			dsPage = monRepository.dsALL(StringUtil.DANGHOATDONG, pageable);
		}else {
			dsPage = monRepository.dsMonTheoLoai(idloai,StringUtil.DANGHOATDONG,pageable);
		}
		List<MonDTO> dsDTO = new ArrayList<>();
		if(dsPage!=null) {
			dsDTO = dsPage.stream().map(MonConverter::toDTO).collect(Collectors.toList());
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
	public ResultEntity<List<MonDTO>> dsAll(String loai) {
		ResultEntity<List<MonDTO>> result = new ResultEntity<>();
		List<Mon> dsAll = monRepository.dsMon(StringUtil.DANGHOATDONG,loai);
		List<MonDTO> dsDTO = new ArrayList<>();
		if(dsAll!=null) {
			dsDTO = dsAll.stream().map(MonConverter::toDTO).collect(Collectors.toList());
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
	public ResultEntity<MonDTO> timMon(String id) {
		ResultEntity<MonDTO> result = new ResultEntity<>();
		Optional<Mon> monOptional = monRepository.findById(id);
		MonDTO dto = MonConverter.toDTO(monOptional.get());
		result.setDulieu(dto);
		result.setKetqua(StringUtil.THANHCONG);
		result.setNoidung("thành công!");
		return result;
	}

	@Override
	public int slPage(String loai,String trangthai) {
		int slmon;
		if(loai.equalsIgnoreCase("allmon")) {
			slmon =monRepository.slmon(trangthai);
		}else {
			slmon = monRepository.slmonTheoLoai(loai, trangthai);
		}
		int slPage = slmon/15;
		if(slmon%15>0) {
			slPage++;
		}
		return slPage;
	}

	@Override
	@Transactional
	public ResultEntity<MonDTO> xoa(String id, String nvId) {
		ResultEntity<MonDTO> result = new ResultEntity<>();
		Optional<NhanVien> nvOptional = nhanVienRepository.findById(nvId);
		Optional<Mon> monOptional = monRepository.findById(id);
		if (nvOptional.isPresent()) {			
			monOptional.get().setNgaycapnhat(new Date());
			monOptional.get().setTrangthai(StringUtil.DAXOA);
			result.setDulieu(MonConverter.toDTO(monOptional.get()));
			result.setKetqua(StringUtil.THANHCONG);
			result.setNoidung("OK!");
			return result;
		}
		result.setDulieu(MonConverter.toDTO(monOptional.get()));
		result.setKetqua(StringUtil.THATBAI);
		result.setNoidung("Xóa thất bại");
		return result;
	}

	@Override
	public ResultEntity<List<MonDTO>> tim(String keyword) {
		ResultEntity<List<MonDTO>> result = new ResultEntity<>();
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
		try {
			fullTextEntityManager.createIndexer().startAndWait();
		} catch (InterruptedException e) {
			result.setDulieu(new ArrayList<MonDTO>());
			return result;
		}
		QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
				.buildQueryBuilder()
				.forEntity(Mon.class)
				.get();
				
		org.apache.lucene.search.Query luceneQuery = queryBuilder
			.keyword().fuzzy()
			.withEditDistanceUpTo(1)
			.withPrefixLength(1)
			.onFields("ten","ghichu")
				.boostedTo(5f)
			.matching(keyword)
			.createQuery();
		
		FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Mon.class);
		List<Mon> dsSanh = jpaQuery.getResultList();
		
		List<MonDTO> dsDTO = dsSanh.stream().filter(x->x.getTrangthai().equalsIgnoreCase(StringUtil.DANGHOATDONG)).map(MonConverter::toDTO).collect(Collectors.toList());
		result.setDulieu(dsDTO);
		return result;
	}

}
