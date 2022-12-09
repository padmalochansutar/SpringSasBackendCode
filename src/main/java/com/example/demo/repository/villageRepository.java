package com.example.demo.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Village;

public interface villageRepository extends JpaRepository<Village, Serializable> {
    @Query("from Village where blockId=:id")
	List<Village> findByBlockId(Integer id);

}
