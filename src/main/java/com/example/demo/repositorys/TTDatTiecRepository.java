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

import com.example.demo.entitys.TTDatTiec;

@Repository
public interface TTDatTiecRepository extends JpaRepository<TTDatTiec, String>{
	
	@Query("SELECT m FROM TTDatTiec m WHERE m.nguoidung.id = :id AND m.trangthai = :trangthai")
	public Page<TTDatTiec> timTheoIdKh(@Param("id")String id,@Param("trangthai")String trangthai,Pageable pageable);
	
	@Query("SELECT m FROM TTDatTiec m WHERE m.nguoidung.id = :idnd AND m.id = :id")
	public Optional<TTDatTiec> timId(@Param("idnd")String idnd,@Param("id")String id);
	
	@Query("SELECT COUNT(m.id) FROM TTDatTiec m WHERE m.nguoidung.id = :idnd AND m.trangthai = :trangthai")
	public int slTTDT(@Param("idnd")String idnd,@Param("trangthai")String trangthai);
	
	@Query("SELECT m FROM TTDatTiec m WHERE m.trangthai = :trangthai")
	public Page<TTDatTiec> dsTheoTT(@Param("trangthai")String trangthai,Pageable pageable);
	
	@Query("SELECT COUNT(m.id) FROM TTDatTiec m WHERE m.trangthai = :trangthai")
	public int slTTDTAdmin(@Param("trangthai")String trangthai);

	@Query("SELECT m FROM TTDatTiec m WHERE m.id = :id AND m.trangthai =:trangthai")
	Optional<TTDatTiec> timTheoTT(@Param("id")String id,@Param("trangthai")String trangthai);
	
	@Query("SELECT m FROM TTDatTiec m WHERE m.ngaytochuc between :startDay and :endDay")
	List<TTDatTiec> dsDDThongKe(@Param("startDay")Date startDay,@Param("endDay")Date endDay);
	
	@Query("SELECT m FROM TTDatTiec m WHERE m.sanh.id = :id AND m.catochuc = :catochuc AND m.ngaytochuc between :startDay and :endDay")
	Optional<TTDatTiec> timTTDTTheoNgay (@Param("id")String id,@Param("catochuc")String catochuc,@Param("startDay")Date startDay,@Param("endDay")Date endDay);
	
}
