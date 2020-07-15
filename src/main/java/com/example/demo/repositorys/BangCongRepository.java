package com.example.demo.repositorys;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entitys.BangCong;
import com.example.demo.entitys.NhanVien;

@Repository
public interface BangCongRepository extends JpaRepository<BangCong, String>{
	
	@Query("SELECT m FROM BangCong m WHERE m.nhanvien.id = :id AND m.ngaycong between :startDay AND :endDay")
	List<BangCong> dsCongNhanVien(@Param("id")String nvId,@Param("startDay")Date startDay,@Param("endDay")Date endDay);
	
	
	@Query("SELECT m FROM BangCong m JOIN m.nhanvien n WHERE n.trangthai = :trangthai AND n.chucvu = :chucvu AND m.ngaycong between :startDay AND :endDay")
	public List<BangCong> dsCongTheoNgay(@Param("trangthai")String trangthai,
			@Param("chucvu")String chucvu,
			@Param("startDay")Date startDay,
			@Param("endDay")Date endDay);
	

}
