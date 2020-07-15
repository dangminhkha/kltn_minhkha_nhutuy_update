package com.example.demo.services.impls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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

import com.example.demo.converters.ComboConverter;
import com.example.demo.converters.MonConverter;
import com.example.demo.dtos.CTComboDTO;
import com.example.demo.dtos.ComboDTO;
import com.example.demo.dtos.LoaiMonDTO;
import com.example.demo.dtos.MonDTO;
import com.example.demo.entitys.CTCombo;
import com.example.demo.entitys.Combo;
import com.example.demo.entitys.Mon;
import com.example.demo.entitys.NhanVien;
import com.example.demo.entitys.ResultEntity;
import com.example.demo.factorys.CTComboFactory;
import com.example.demo.factorys.ComboFactory;
import com.example.demo.repositorys.ComboRepository;
import com.example.demo.repositorys.MonRepository;
import com.example.demo.repositorys.NhanVienRepository;
import com.example.demo.services.ComboService;
import com.example.demo.utils.RandomStringExmple;
import com.example.demo.utils.StringUtil;

@Service
public class ComboServiceImpl implements ComboService {
	@Autowired
	private NhanVienRepository nhanVienRepository;
	@Autowired
	private ComboRepository comboRepository;
	@Autowired
	private MonRepository monReponsitory;
	@Autowired
	private EntityManager em;

	@Override
	public ResultEntity<ComboDTO> themCombo(String id, ComboDTO dto) {
		ResultEntity<ComboDTO> result = new ResultEntity<>();
		Optional<NhanVien> nvOptional = nhanVienRepository.findById(id);
		System.out.println("TỚi đây: "+nvOptional.get().getId());
		if (nvOptional.isPresent()) {
			String idCombo = RandomStringExmple.id(6);
			Combo entity = ComboFactory.getInstance();
			entity.setId(idCombo);
			entity.setTen(dto.getTen());
			entity.setGia(dto.getGia());
			entity.setNhanvien(nvOptional.get());
			List<CTCombo> ctcombos = dto.getCtcombos().stream().map(x -> {
				CTCombo c = CTComboFactory.getInstance();
				c.setStt(x.getStt());
				c.setCombo(entity);
				Optional<Mon> m = monReponsitory.timTen(x.getMon().getTen(), StringUtil.DANGHOATDONG);
				c.setMon(m.get());
				return c;
			}).collect(Collectors.toList());
			entity.getCtcombos().addAll(ctcombos);
			entity.setHinh(dto.getHinh());
			entity.setGhichu(dto.getGhichu());
			comboRepository.save(entity);
			result.setDulieu(ComboConverter.toDTO(entity));
			result.setKetqua(StringUtil.THANHCONG);
			result.setNoidung("Thêm thành công!");
			return result;

		}
		result.setKetqua(StringUtil.THATBAI);
		result.setNoidung("Thêm thất bại!");
		return result;
	}

	@Override
	public ResultEntity<List<ComboDTO>> dsComBo(int page, int size) {
		ResultEntity<List<ComboDTO>> result = new ResultEntity<>();
		Pageable pageable = PageRequest.of(page, size);
		Page<Combo> dsPage = comboRepository.dsALL(StringUtil.DANGHOATDONG, pageable);
		List<ComboDTO> dsDTO = new ArrayList<>();
		if (dsPage != null) {
			dsDTO = dsPage.stream().map(ComboConverter::toDTO).collect(Collectors.toList());
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
	public ResultEntity<List<ComboDTO>> dsChon() {
		ResultEntity<List<ComboDTO>> result = new ResultEntity<>();
		List<Combo> dsAll = comboRepository.dsChon(StringUtil.DANGHOATDONG);
		List<ComboDTO> dsDTO = new ArrayList<>();
		if (dsAll != null) {
			dsDTO = dsAll.stream().map(ComboConverter::toDTO).collect(Collectors.toList());
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
	public ResultEntity<ComboDTO> timID(String id) {
		ResultEntity<ComboDTO> result = new ResultEntity<>();
		Optional<Combo> comboOptional = comboRepository.findById(id);
		ComboDTO dto = ComboConverter.toDTO(comboOptional.get());
		result.setDulieu(dto);
		result.setKetqua(StringUtil.THANHCONG);
		result.setNoidung("Đã tìm thấy!");
		return result;
	}

	@Override
	public int slPage(String trangthai) {
		int slcombo = comboRepository.slcombo(trangthai);
		int slPage = slcombo/15;
		if(slcombo%15>0) {
			slPage++;
		}
		return slPage;
	}

	@Override
	@Transactional
	public ResultEntity<ComboDTO> xoa(String id, String nvId) {
		ResultEntity<ComboDTO> result = new ResultEntity<>();
		Optional<NhanVien> nvOptional = nhanVienRepository.findById(nvId);
		Optional<Combo> comOptional = comboRepository.findById(id);
		if (nvOptional.isPresent()) {			
			comOptional.get().setNgaycapnhat(new Date());
			comOptional.get().setTrangthai(StringUtil.DAXOA);
			result.setDulieu(ComboConverter.toDTO(comOptional.get()));
			result.setKetqua(StringUtil.THANHCONG);
			result.setNoidung("OK!");
			return result;
		}
		result.setDulieu(ComboConverter.toDTO(comOptional.get()));
		result.setKetqua(StringUtil.THATBAI);
		result.setNoidung("Xóa thất bại");
		return result;
	}

	@Override
	public ResultEntity<List<ComboDTO>> tim(String keyword) {
		ResultEntity<List<ComboDTO>> result = new ResultEntity<>();
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
		try {
			fullTextEntityManager.createIndexer().startAndWait();
		} catch (InterruptedException e) {
			result.setDulieu(new ArrayList<ComboDTO>());
			return result;
		}
		QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
				.buildQueryBuilder()
				.forEntity(Combo.class)
				.get();
				
		org.apache.lucene.search.Query luceneQuery = queryBuilder
			.keyword().fuzzy()
			.withEditDistanceUpTo(1)
			.withPrefixLength(1)
			.onFields("ten","ghichu")
				.boostedTo(5f)
			.matching(keyword)
			.createQuery();
		
		FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Combo.class);
		List<Combo> dsSanh = jpaQuery.getResultList();
		
		List<ComboDTO> dsDTO = dsSanh.stream().filter(x->x.getTrangthai().equalsIgnoreCase(StringUtil.DANGHOATDONG))
				.map(ComboConverter::toDTO).collect(Collectors.toList());
		result.setDulieu(dsDTO);
		return result;
	}

}
