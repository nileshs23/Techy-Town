package com.techytown.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.techytown.model.Customer;

@Repository
public interface CustomerRespository extends JpaRepository<Customer, Integer>{

}
