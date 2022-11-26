package com.techytown.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.techytown.model.Role;
import com.techytown.model.User;
import com.techytown.repository.RoleRepository;
import com.techytown.repository.UserRespository;
import com.techytown.services.UserService;

@Controller
public class LoginController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@GetMapping("/index")
    public String home(){
        return "index";
    }

	
	@GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }
	
	@PostMapping("/register/save")
	public String registerUser(@Valid @ModelAttribute("user") User user, Model model) {
		
		userService.registerUser(user);
		
		return "redirect:/";
	}
	
	
	

	
	
}
