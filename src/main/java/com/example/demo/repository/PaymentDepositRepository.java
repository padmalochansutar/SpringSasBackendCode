package com.example.demo.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.District;
import com.example.demo.entity.PaymentDeposit;



public interface PaymentDepositRepository extends JpaRepository<PaymentDeposit, Serializable> {
    @Query("from PaymentDeposit p where p.register.registerId=:sId")
	Optional<PaymentDeposit> findByRegister(Integer sId);
   // @Query("delete from PaymentDeposit p where p.register.registerId=:id")
	//void deletedata(Integer id);
    @Modifying
    @Transactional()
    @Query("delete from PaymentDeposit p where p.paymentId=:Id")
	Integer deletedataById(Integer Id);
    @Query("From  PaymentDeposit p where p.id=:id")
    PaymentDeposit findDataId(Integer id);

}
