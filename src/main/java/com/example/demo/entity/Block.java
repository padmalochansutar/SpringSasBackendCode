package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
@Data
@Entity
public class Block {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer blockId;
	
	private String blockName;
	@ManyToOne
	@JoinColumn(name="districtId")
	private District district;

}
