package com.example.demo.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entitys.CTNuocDatTiec;

@Repository
public interface CTNuocDatTiecRepository extends JpaRepository<CTNuocDatTiec, String>{

}
