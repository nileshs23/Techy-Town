package com.techytown.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techytown.exception.CartException;
import com.techytown.exception.UserException;
import com.techytown.exception.ProductException;
import com.techytown.model.Cart;
import com.techytown.model.User;
import com.techytown.model.Product;
import com.techytown.services.CartService;
import com.techytown.services.UserService;
import com.techytown.services.ProductService;

@RestController
public class UserController {

	@Autowired
	private UserService userServ;
	
	@Autowired
	private CartService cartServ;
	
	@Autowired
	private ProductService productServ;
	
	
//	@GetMapping("/")
//	public ResponseEntity<String> helloUsingSecurity(){
//		return new ResponseEntity<String>("Hello Security Is Here !",HttpStatus.CREATED);
//	}
	
	
	@PostMapping("/customer")
	public ResponseEntity<User> registerCustomer(@Valid @RequestBody User user){
		User signup = userServ.registerUser(user);
		return new ResponseEntity<User>(signup,HttpStatus.CREATED);
	}

}
