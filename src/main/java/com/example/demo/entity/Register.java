package com.example.demo.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;
@Data
@Entity
public class Register {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "regd_id")
	private Integer registerId;
	
	private String userName;
	
	private String fName;
	
	private String mName;
	
	private String dob;
	
	private String gender;
	
	private String course;
	@ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
	@JoinTable(
			  name = "my_edu_my_reg", 
			  joinColumns = {@JoinColumn(name = "regd_id")}, 
			  inverseJoinColumns = { @JoinColumn(name = "id")})
	private List<CourseEntity> edu;
	
	private String state;
	private String file1;
	private String file;
	@ManyToOne
	@JoinColumn(name="districtId")
	private District district;
	
	@ManyToOne
	@JoinColumn(name="blockId")
	private Block block;
	@ManyToOne
	@JoinColumn(name="villageId")
	private Village village;

	private Integer id;
	
}
