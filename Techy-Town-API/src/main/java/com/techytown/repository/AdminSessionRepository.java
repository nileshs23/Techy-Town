package com.techytown.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techytown.model.CurrentAdminSession;


@Repository
public interface AdminSessionRepository extends JpaRepository<CurrentAdminSession, Integer> {

	public Optional<CurrentAdminSession> findByUuid(String uuid);
	
	public Optional<CurrentAdminSession> findByAdminId(Integer adminId); 
	
}
