package com.example.demo.bean;

import java.util.List;

import com.example.demo.entity.College;

import lombok.Data;

@Data
public class CollegeRegisterBean {
	
	private List<College> collegeId;
	
	private Integer registerId;

}
