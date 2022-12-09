package com.example.demo.bean;

import java.util.List;

import com.example.demo.entity.CourseEntity;
import com.example.demo.entity.Education;

import lombok.Data;



@Data
public class RegisterBean {
	
	private String userName;
	
	private String fatherName;
	
	private String motherName;
	
	private String dob;
	
	private String gender;
	
	private String course;
	
	private List<CourseEntity> eduObj;
	
	private String state ;
	
	private String file;
	
	private String file1;
	
	private Integer districtId;
	
	private Integer blockId;
	private Integer villageObj;
	
	
	
	
	
}
