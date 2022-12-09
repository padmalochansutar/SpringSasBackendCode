package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;
@Data
@Entity
public class PaymentDeposit {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer paymentId;
	@ManyToOne
	@JoinColumn(name="castId")
	private Cast cast;
	
	private Long accNo;
	
	private Double payment;
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="regd_id")
	private Register register;
	
	
	private Integer id;
	

}
