package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

import lombok.Data;
@Data
@Entity
public class College {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer collegeId;
	
	private String collegeName;
	
	
	
	
}
