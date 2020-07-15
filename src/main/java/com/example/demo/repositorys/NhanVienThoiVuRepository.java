package com.example.demo.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entitys.NhanVienThoiVu;

@Repository
public interface NhanVienThoiVuRepository extends JpaRepository<NhanVienThoiVu, String>{
	
	@Query("SELECT m FROM NhanVienThoiVu m WHERE m.nhomThoiVu.id = :nhomid AND m.cmnd = :cmnd AND m.trangthai = :trangthai")
	Optional<NhanVienThoiVu> timCMND(@Param("nhomid")String nhomid, @Param("cmnd")String cmnd,@Param("trangthai")String trangthai);

}
