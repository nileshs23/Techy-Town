package com.techytown.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techytown.repository.RoleRepository;
import com.techytown.repository.UserRespository;

@RestController
public class LoginController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRespository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@GetMapping("/login")
	public String login() {
		return "Logged In";
	}
	
	@GetMapping("/register")
	public String register() {
		return "Registered";
	}
	
	
}
