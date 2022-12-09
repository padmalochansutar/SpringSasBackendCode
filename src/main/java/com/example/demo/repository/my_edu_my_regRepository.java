package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.my_edu_my_reg;

public interface my_edu_my_regRepository extends JpaRepository<my_edu_my_reg, Integer>{
    @Query("from my_edu_my_reg m where m.regd_id=:sId")
	List<my_edu_my_reg> findAll(Integer sId);

}
