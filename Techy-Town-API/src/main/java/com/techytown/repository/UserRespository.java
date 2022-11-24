package com.techytown.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.techytown.model.User;

@Repository
public interface UserRespository extends JpaRepository<User, Integer>{

	
	Optional<User> findUserByEmail(String email);
}
