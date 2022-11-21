package com.techytown.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techytown.model.Customer;
import com.techytown.services.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerServ;
	
	
	@PostMapping("/customer")
	public ResponseEntity<Customer> registerCustomer(@Valid @RequestBody Customer customer){
		Customer signup = customerServ.registerCustomer(customer);
		return new ResponseEntity<Customer>(signup,HttpStatus.CREATED);
	}
	
}
