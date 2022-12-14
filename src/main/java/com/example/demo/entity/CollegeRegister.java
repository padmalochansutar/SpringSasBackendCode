package com.example.demo.entity;

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
import javax.persistence.OneToMany;

import lombok.Data;
@Data
@Entity
public class CollegeRegister {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer CollegeRegisterId;
    @ManyToOne
	@JoinColumn(name="college_id")
	private College college;
	@ManyToOne
	@JoinColumn(name="regd_id")
	private Register register;
	
	
	

}
