package com.example.demo.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entitys.LoaiMon;

@Repository
public interface LoaiMonRepository extends JpaRepository<LoaiMon, String>{
	@Query("SELECT m FROM LoaiMon m WHERE m.ten = :ten AND m.trangthai = :trangthai")
	public Optional<LoaiMon> timTenLoai(@Param("ten")String ten,@Param("trangthai")String trangthai);
	
	@Query("SELECT m FROM LoaiMon m WHERE m.trangthai = :trangthai")
	public List<LoaiMon> loaiMonAll(@Param("trangthai")String trangthai);
	
	@Query("SELECT m FROM LoaiMon m WHERE m.trangthai = :trangthai")
	public Page<LoaiMon> pageLoaiMon(@Param("trangthai")String trangthai,Pageable pageable);
	
	@Query("SELECT COUNT(m.id) FROM LoaiMon m WHERE m.trangthai = :trangthai")
	public int slloai(@Param("trangthai")String trangthai);
	
	@Query("SELECT m FROM LoaiMon m WHERE m.trangthai = :trangthai AND m.loai = :loai")
	public List<LoaiMon> loaiMonAns(@Param("trangthai")String trangthai, @Param("loai")String loai);
}
