package com.example.demo.repositorys;

import com.example.demo.entitys.Combo;
import com.example.demo.entitys.Mon;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ComboRepository extends JpaRepository<Combo, String>{
	@Query("SELECT m FROM Combo m WHERE m.ten = :ten AND m.trangthai = :trangthai")
	public Optional<Combo> timTen(@Param("ten")String ten,@Param("trangthai")String trangthai);
	
	@Query("SELECT m FROM Combo m WHERE m.trangthai = :trangthai")
	public Page<Combo> dsALL(@Param("trangthai")String trangthai,Pageable pageable);
	
	@Query("SELECT m FROM Combo m WHERE m.trangthai = :trangthai")
	public List<Combo> dsChon(@Param("trangthai")String trangthai);
	
	@Query("SELECT COUNT(m.id) FROM Combo m WHERE m.trangthai = :trangthai")
	public int slcombo(@Param("trangthai")String trangthai);
}
