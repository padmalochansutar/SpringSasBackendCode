package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.StudentSignin;




@Repository
public interface StudentSigninRepository extends JpaRepository<StudentSignin, Integer> {
    
	StudentSignin searchByuserNameAndPassword(String userName, String password);

     StudentSignin getByEmailId(String emailId);

	StudentSignin getByMobileNo(Long mobileNo);

	StudentSignin findByUserNameAndPassword(String userName, String password);

	StudentSignin findByEmailId(String emailId);

}
