package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import lombok.Data;
@Data
@Entity
public class my_edu_my_reg {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer regid;
	@JoinColumn(name = "regd_id")
	private Integer regd_id;
	
	private Integer id;

}
