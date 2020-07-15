package com.example.demo.repositorys;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entitys.NguoiDung;
import com.example.demo.entitys.NhanVien;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, String>{
	@Query("SELECT m FROM NhanVien m WHERE (m.sodienthoai = :sodienthoai "
			+ "OR m.email = :email) "
			+ "AND m.matkhau = :matkhau  "
			+ "AND m.trangthai = :trangthai ")
	public Optional<NhanVien> dangNhap(@Param("sodienthoai")String sodienthoai,
			@Param("email")String email,
			@Param("matkhau")String matkhau,
			@Param("trangthai")String trangthai);
	
	@Query("SELECT m FROM NhanVien m WHERE m.trangthai = :trangthai AND m.chucvu =:chucvu")
	public Page<NhanVien> dsNhanVien(@Param("trangthai")String trangthai,@Param("chucvu")String chucvu,Pageable pageable);
	
	@Query("SELECT m FROM NhanVien m WHERE m.trangthai = :trangthai AND m.chucvu = :chucvu")
	public List<NhanVien> dsAll(@Param("trangthai")String trangthai,@Param("chucvu")String chucvu);
	
	@Query("SELECT m FROM NhanVien m WHERE m.id = :id "
			+ "AND m.chucvu = :chucvu "
			+ "AND m.trangthai = :trangthai")
	public Optional<NhanVien> timId(@Param("id")String id,
			@Param("chucvu")String chucvu,
			@Param("trangthai")String trangthai);
	@Query("SELECT m FROM NhanVien m WHERE (m.sodienthoai = :sodienthoai "
			+ "OR m.email = :email) AND m.trangthai = :trangthai")
	public Optional<NhanVien> timSdtEmail(@Param("sodienthoai")String sodienthoai,
			@Param("email")String email,@Param("trangthai")String trangthai);
	
	@Query("SELECT m FROM NhanVien m WHERE m.sodienthoai = :sodienthoai")
	public Optional<NhanVien> timSdt(@Param("sodienthoai")String sodienthoai);
	
	@Query("SELECT m FROM NhanVien m WHERE m.email = :email")
	public Optional<NhanVien> timEmail(@Param("email")String email);
	
	@Query("SELECT COUNT(m.id) FROM NhanVien m WHERE m.trangthai = :trangthai AND m.chucvu = :chucvu")
	public int slNhanVien(@Param("trangthai")String trangthai,@Param("chucvu")String chucvu);
	
	@Query("SELECT m FROM NhanVien m WHERE m.id = :id "
			+ "AND m.trangthai = :trangthai "
			+ "AND m.matkhau = :matkhau")
	public Optional<NhanVien> timIdMk(@Param("id")String id,
			@Param("trangthai")String trangthai,
			@Param("matkhau")String matkhau);
	
	@Query("SELECT m FROM NhanVien m WHERE m.trangthai = :trangthai AND m.chucvu = :loai")
	public List<NhanVien> dsNhanVienChamCong(@Param("trangthai")String trangthai,
			@Param("loai")String loai);
	
	@Query("SELECT m FROM NhanVien m JOIN m.bangCongs n WHERE m.trangthai = :trangthai AND m.chucvu = :chucvu AND n.ngaycong between :startDay AND :endDay")
	public List<NhanVien> dsNhanVienTheoNgay(@Param("trangthai")String trangthai,
			@Param("chucvu")String chucvu,
			@Param("startDay")Date startDay,
			@Param("endDay")Date endDay);
	

}
