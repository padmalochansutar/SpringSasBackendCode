package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
@Data
@Entity
public class Payment {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer paymentId;
	
	private Double fee;
	@OneToOne
	@JoinColumn(name="castId")
	private Cast cast;

}
