package com.example.demo.repository;

import java.io.Serializable;

import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Register;

public interface RegisterRepository extends JpaRepository<Register, Serializable> {
   // @Query("select count(*)from Register where userName=:name and fName=:faName and mName=:moName")
	 @Query("from Register where userName=:name and fName=:faName and mName=:moName")
	Register getByName(String name, String faName, String moName);
    @Query("from Register where id=:sId")
	Register finddataById(Integer sId);
    @Query("from Register where registerId=:fileId")
	Register getfilebyId(Integer fileId);
    
    
//    @Query("from Register where file=:filename")
//	Register download(String filename);

}
