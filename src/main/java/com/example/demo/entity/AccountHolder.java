package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
@Data
@Entity
public class AccountHolder {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer accountId;
	
	private Long accountNo;
	
	private String accHolderName;

}
