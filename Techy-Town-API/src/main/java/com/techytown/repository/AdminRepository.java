package com.techytown.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.techytown.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{

	@Query("SELECT a FROM Admin a WHERE a.contactNo=?1")
	public Admin findByContactNo(String contactNo);
	
	
}
