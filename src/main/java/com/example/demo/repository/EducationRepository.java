package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Education;

public interface EducationRepository extends JpaRepository<Education, Integer> {

}
