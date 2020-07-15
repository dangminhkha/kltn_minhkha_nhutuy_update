package com.example.demo.services.impls;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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

import com.example.demo.converters.SanhConverter;
import com.example.demo.dtos.SanhDTO;
import com.example.demo.dtos.TTDatTiecDTO;
import com.example.demo.entitys.NhanVien;
import com.example.demo.entitys.ResultEntity;
import com.example.demo.entitys.Sanh;
import com.example.demo.factorys.SanhFactory;
import com.example.demo.repositorys.NhanVienRepository;
import com.example.demo.repositorys.SanhRepository;
import com.example.demo.services.SanhService;
import com.example.demo.utils.DateUI;
import com.example.demo.utils.RandomStringExmple;
import com.example.demo.utils.StringUtil;

@Service
public class SanhServiceImpl implements SanhService {
	@Autowired
	private NhanVienRepository nhanVienRepository;
	@Autowired
	private SanhRepository sanhRepository;
	@Autowired
	private EntityManager em;

	@Override
	public ResultEntity<SanhDTO> themSanh(String id, SanhDTO dto) {
		ResultEntity<SanhDTO> result = new ResultEntity<>();
		Optional<NhanVien> nvOptional = nhanVienRepository.findById(id);
		if (!nvOptional.isPresent()) {
			result.setDulieu(dto);
			result.setKetqua(StringUtil.THATBAI);
			result.setNoidung("Bạn cần đăng nhập tài khoản quản lý!");
			return result;
		}
		System.out.println(dto.getTen());

		Optional<Sanh> tenSanhOptional = sanhRepository.timTen(dto.getTen(), StringUtil.DANGHOATDONG);
		if (tenSanhOptional.isPresent()) {
			result.setDulieu(dto);
			result.setKetqua(StringUtil.THATBAI);
			result.setNoidung("Tên sảnh đã tồn tại!");
			return result;
		}
		String idSanh = RandomStringExmple.id(6);
		Sanh entity = SanhFactory.getInstane();
		entity.setId(idSanh);
		entity.setTen(dto.getTen());
		entity.setSokhachtoida(dto.getSokhachtoida());
		entity.setSokhachtoithieu(dto.getSokhachtoithieu());
		entity.setNhanvien(nvOptional.get());
		entity.setHinh(dto.getHinh());
		entity.setMota(dto.getMota());
		sanhRepository.save(entity);
		result.setDulieu(SanhConverter.toDTO(entity));
		result.setKetqua(StringUtil.THANHCONG);
		result.setNoidung("Thêm thành công");
		return result;
	}

	@Override
	public ResultEntity<List<SanhDTO>> dsSanh(int page, int size) {
		ResultEntity<List<SanhDTO>> result = new ResultEntity<>();

		Pageable pageable = PageRequest.of(page, size);
		Page<Sanh> dsPage = sanhRepository.dsALL(StringUtil.DANGHOATDONG, pageable);

		List<SanhDTO> dsDTO = new ArrayList<>();
		if (dsPage != null) {
			dsDTO = dsPage.stream().map(SanhConverter::toDTO).collect(Collectors.toList());
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
	public ResultEntity<List<SanhDTO>> dsSanhCho(TTDatTiecDTO dto) {
		ResultEntity<List<SanhDTO>> result = new ResultEntity<>();
		Date dtc = DateUI.formatDateUI(dto.getNgaytochuc(), StringUtil.MOI);
		Date dss = DateUI.formatDateUI(dto.getNgaytochuc(), StringUtil.CU);
		SimpleDateFormat dfm = new SimpleDateFormat("yyyyMMdd");
		List<Sanh> dsSanh = sanhRepository.dsCho(StringUtil.DANGHOATDONG);
		Set<Sanh> s = new HashSet<Sanh>();
		dsSanh.stream().map(x->{
			AtomicBoolean kiemtra = new AtomicBoolean(true); 
			x.getTtDatTiecs().stream().map(z->{
				if(dfm.format(dss).equalsIgnoreCase(dfm.format(z.getNgaytochuc()))) {
					if (z.getCatochuc().equalsIgnoreCase(dto.getCatochuc())) {
						if(!z.getTrangthai().equalsIgnoreCase(StringUtil.HUY)) {
							kiemtra.set(false);
						}
					}
				}
				return z;
			}).collect(Collectors.toList());
			if (kiemtra.get()==true) {
				if(x.getSokhachtoithieu()<=dto.getSokhach() && x.getSokhachtoida()>=dto.getSokhach()) {
					s.add(x);
				}
			}
			return x;
		}).collect(Collectors.toList());
		
		List<SanhDTO> dsDTO = new ArrayList<>();
		if (dsSanh != null) {
			dsDTO = s.stream().map(SanhConverter::toDTO).collect(Collectors.toList());
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
	public ResultEntity<SanhDTO> timId(String id) {
		ResultEntity<SanhDTO> result = new ResultEntity<>();
		Optional<Sanh> sanhOptional = sanhRepository.findById(id);
		SanhDTO dto = SanhConverter.toDTO(sanhOptional.get());
		result.setDulieu(dto);
		result.setKetqua(StringUtil.THANHCONG);
		result.setNoidung("OK!");
		return result;
	}

	@Override
	public int slPage(String trangthai) {
		int slSanh = sanhRepository.slSanh(trangthai);
		int slPage = slSanh/15;
		if(slSanh%15>0) {
			slPage++;
		}
		return slPage;
	}

	@Override
	@Transactional
	public ResultEntity<SanhDTO> xoa(String id, String nvId) {
		ResultEntity<SanhDTO> result = new ResultEntity<>();
		Optional<NhanVien> nvOptional = nhanVienRepository.findById(nvId);
		if (nvOptional.isPresent()) {
			AtomicBoolean atomicBoolean = new AtomicBoolean(false);
			Optional<Sanh> sanhOptional = sanhRepository.findById(id);
			sanhOptional.get().getTtDatTiecs().stream()
			.map(x->{
				if (!x.getTrangthai().equalsIgnoreCase(StringUtil.HUY) && !x.getTrangthai().equalsIgnoreCase(StringUtil.DATHANHTOAN)) {
					atomicBoolean.set(true);
				}
				return x;
			}).collect(Collectors.toList());
			if (atomicBoolean.get() == false) {
				sanhOptional.get().setNgaycapnhat(new Date());
				sanhOptional.get().setTrangthai(StringUtil.DAXOA);
				result.setDulieu(SanhConverter.toDTO(sanhOptional.get()));
				result.setKetqua(StringUtil.THANHCONG);
				result.setNoidung("OK!");
				return result;
			}
			result.setDulieu(SanhConverter.toDTO(sanhOptional.get()));
			result.setKetqua(StringUtil.THATBAI);
			result.setNoidung("không thể xóa vì sắp tới có tiệc được tổ chức ở sãnh này");
			return result;
		}
		result.setKetqua(StringUtil.THATBAI);
		result.setNoidung("Xóa thất bại");
		return result;
	}
	
	@Override
	public ResultEntity<List<SanhDTO>> timKeyword(String keyword) {
		ResultEntity<List<SanhDTO>> result = new ResultEntity<>();
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
		try {
			fullTextEntityManager.createIndexer().startAndWait();
		} catch (InterruptedException e) {
			result.setDulieu(new ArrayList<SanhDTO>());
			return result;
		}
		QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
				.buildQueryBuilder()
				.forEntity(Sanh.class)
				.get();
				
		org.apache.lucene.search.Query luceneQuery = queryBuilder
			.keyword().fuzzy()
			.withEditDistanceUpTo(1)
			.withPrefixLength(1)
			.onFields("ten")
				.boostedTo(5f)
			.matching(keyword)
			.createQuery();
		
		FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Sanh.class);
		List<Sanh> dsSanh = jpaQuery.getResultList();
		
		List<SanhDTO> dsDTO = dsSanh.stream().filter(x->x.getTrangthai().equalsIgnoreCase(StringUtil.DANGHOATDONG)).map(SanhConverter::toDTO).collect(Collectors.toList());
		result.setDulieu(dsDTO);
		return result;
	}

}
