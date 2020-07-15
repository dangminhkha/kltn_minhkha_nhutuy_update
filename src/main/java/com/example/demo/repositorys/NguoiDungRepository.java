package com.example.demo.repositorys;

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
public interface NguoiDungRepository extends JpaRepository<NguoiDung, String>{
	@Query("SELECT m FROM NguoiDung m WHERE m.sodienthoai = :sodienthoai "
			+ "OR m.email = :email")
	public Optional<NguoiDung> timSdtEmail(@Param("sodienthoai")String sodienthoai,
			@Param("email")String email);
	
	@Query("SELECT m FROM NguoiDung m WHERE m.sodienthoai = :sodienthoai")
	public Optional<NguoiDung> timSDT(@Param("sodienthoai")String sodienthoai);
	
	@Query("SELECT m FROM NguoiDung m WHERE m.email = :email")
	public Optional<NguoiDung> timEmail(@Param("email")String email);
	
	@Query("SELECT m FROM NguoiDung m WHERE (m.sodienthoai = :sodienthoai "
			+ "OR m.email = :email) "
			+ "AND m.matkhau = :matkhau  "
			+ "AND m.trangthai = :trangthai ")
	public Optional<NguoiDung> dangNhap(@Param("sodienthoai")String sodienthoai,
			@Param("email")String email,
			@Param("matkhau")String matkhau,
			@Param("trangthai")String trangthai);

	@Query("SELECT m FROM NguoiDung m WHERE m.id = :id "
			+ "AND m.trangthai = :trangthai")
	public Optional<NguoiDung> timId(@Param("id")String id,
			@Param("trangthai")String trangthai);
	
	@Query("SELECT m FROM NguoiDung m WHERE m.id = :id "
			+ "AND m.trangthai = :trangthai "
			+ "AND m.matkhau = :matkhau")
	public Optional<NguoiDung> timIdMk(@Param("id")String id,
			@Param("trangthai")String trangthai,
			@Param("matkhau")String matkhau);
	
	@Query("SELECT m FROM NguoiDung m WHERE m.trangthai = :trangthai")
	public Page<NguoiDung> dsKH(@Param("trangthai")String trangthai,Pageable pageable);
	
	@Query("SELECT COUNT(m.id) FROM NguoiDung m WHERE m.trangthai = :trangthai")
	public int slNguoiDung(@Param("trangthai")String trangthai);
}
