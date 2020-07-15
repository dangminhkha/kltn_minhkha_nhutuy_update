package com.example.demo.repositorys;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entitys.HoaDon;
import com.example.demo.entitys.TTDatTiec;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, String>{

	@Query("SELECT m FROM HoaDon m WHERE m.ttDatTiec.nguoidung.id = :idnd AND m.ttDatTiec.id = :id")
	public Optional<HoaDon> timId(@Param("idnd")String idnd,@Param("id")String id);
	
	@Query("SELECT m FROM HoaDon m WHERE m.ttDatTiec.id = :id")
	public Optional<HoaDon> timHDNV(@Param("id")String id);
	
	@Query("SELECT m FROM HoaDon m WHERE m.ngaythanhtoan between :startDay and :endDay")
	List<HoaDon> dsHDThongKe(@Param("startDay")Date startDay,@Param("endDay")Date endDay);
}
