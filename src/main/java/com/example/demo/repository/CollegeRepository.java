package com.example.demo.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.College;
import com.example.demo.entity.CollegeRegister;

public interface CollegeRepository extends JpaRepository<College, Serializable> {
   
}
