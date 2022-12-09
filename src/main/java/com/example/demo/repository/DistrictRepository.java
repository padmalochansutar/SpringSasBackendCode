package com.example.demo.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.District;

public interface DistrictRepository extends JpaRepository<District, Serializable> {

}
