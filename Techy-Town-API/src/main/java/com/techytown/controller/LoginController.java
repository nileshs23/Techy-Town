package com.techytown.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.techytown.model.User;
import com.techytown.services.UserService;

@Controller
public class LoginController {

	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/index")
    public String home(){
        return "index";
    }
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	
	@GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }
	
	@PostMapping("/register/save")
	public String registerUser(@Valid @ModelAttribute("user") User user, 
			Model model) {
		
		userService.registerUser(user);
		
//		request.login(user.getEmail(), user.getPassword());
		return "redirect:/register?success";
	}
	
	

	
	
}
