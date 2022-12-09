package com.example.demo.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Block;

public interface BlockRepository extends JpaRepository<Block, Serializable> {
    @Query("from Block b where districtId=:id")
	List<Block> findBlockByDistrictId(Integer id);

}
