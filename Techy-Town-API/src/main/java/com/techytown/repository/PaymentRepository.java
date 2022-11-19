package com.techytown.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techytown.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer>{

}
