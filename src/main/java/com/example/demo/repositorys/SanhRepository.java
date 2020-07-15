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

import com.example.demo.entitys.Sanh;

@Repository
public interface SanhRepository extends JpaRepository<Sanh, String>{
	
	@Query("SELECT m FROM Sanh m WHERE m.ten = :ten AND m.trangthai = :trangthai")
	public Optional<Sanh> timTen(@Param("ten")String ten,@Param("trangthai")String trangthai);
	
	@Query("SELECT m FROM Sanh m WHERE m.trangthai = :trangthai")
	public Page<Sanh> dsALL(@Param("trangthai")String trangthai,Pageable pageable);
	
	@Query("SELECT m FROM Sanh m WHERE m.trangthai = :trangthai")
	public List<Sanh> dsCho(@Param("trangthai")String trangthai);
	
	@Query("SELECT COUNT(m.id) FROM Sanh m WHERE m.trangthai = :trangthai")
	public int slSanh(@Param("trangthai")String trangthai);
	
}
