package com.example.demo.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entitys.CTCombo;

@Repository
public interface CTComboRepository extends JpaRepository<CTCombo, String>{

}
