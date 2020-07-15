package com.example.demo.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entitys.Combo;
import com.example.demo.entitys.Mon;

@Repository
public interface MonRepository extends JpaRepository<Mon, String>{
	
	@Query("SELECT m FROM Mon m WHERE m.loaimon.id = :id AND m.trangthai = :trangthai")
	public Page<Mon> dsMonTheoLoai(@Param("id")String id,@Param("trangthai")String trangthai,Pageable pageable);

	@Query("SELECT m FROM Mon m WHERE m.trangthai = :trangthai")
	public Page<Mon> dsALL(@Param("trangthai")String trangthai,Pageable pageable);
	
	@Query("SELECT m FROM Mon m WHERE m.trangthai = :trangthai AND m.loaimon.loai = :loai")
	public List<Mon> dsMon(@Param("trangthai")String trangthai,@Param("loai")String loai);
	
	@Query("SELECT m FROM Mon m WHERE m.ten = :ten AND m.trangthai = :trangthai")
	public Optional<Mon> timTen(@Param("ten")String ten,@Param("trangthai")String trangthai);
	
	@Query("SELECT COUNT(m.id) FROM Mon m WHERE m.trangthai = :trangthai")
	public int slmon(@Param("trangthai")String trangthai);
	
	@Query("SELECT COUNT(m.id) FROM Mon m WHERE m.loaimon.id = :id AND m.trangthai = :trangthai")
	public int slmonTheoLoai(@Param("id")String id, @Param("trangthai")String trangthai);
}
