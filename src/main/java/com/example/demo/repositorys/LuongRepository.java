package com.example.demo.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entitys.Luong;

@Repository
public interface LuongRepository extends JpaRepository<Luong, String>{
	
	@Query("SELECT m FROM Luong m WHERE m.nam = :nam AND m.thang = :thang")
	List<Luong> dsTheoNamThang(@Param("nam")int nam,@Param("thang")int thang);
	
	@Query("SELECT m FROM Luong m WHERE m.nam = :nam AND m.nhanvien.id = :nvid")
	List<Luong> dsTheoNhanVien(@Param("nam")int nam,@Param("nvid")String nvid);
 
}
