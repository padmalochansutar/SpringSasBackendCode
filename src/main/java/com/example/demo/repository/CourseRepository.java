package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.CourseEntity;

public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {

	@Query("From CourseEntity where education.eduId =:id")
	public List<CourseEntity> getAllList(Integer id);
}
