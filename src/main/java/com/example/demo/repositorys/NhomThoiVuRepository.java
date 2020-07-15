package com.example.demo.repositorys;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entitys.NhomThoiVu;

@Repository
public interface NhomThoiVuRepository extends JpaRepository<NhomThoiVu, String>{
	
	@Query("SELECT m FROM NhomThoiVu m WHERE m.ngaylam between :startDay and :endDay")
	List<NhomThoiVu> dsNhomTheoNgay(@Param("startDay")Date startDay,@Param("endDay")Date endDay);
	
	@Query("SELECT m FROM NhomThoiVu m WHERE m.id = :id AND m.trangthai = :trangthai")
	Optional<NhomThoiVu> timid(@Param("id")String id,@Param("trangthai")String trangthai);
	
	@Query("SELECT m FROM NhomThoiVu m WHERE m.ten = :ten AND m.trangthai = :trangthai AND (m.ngaylam between :startDay and :endDay)")
	Optional<NhomThoiVu> timTen(@Param("ten")String ten,@Param("trangthai")String trangthai,@Param("startDay")Date startDay,@Param("endDay")Date endDay);

}
