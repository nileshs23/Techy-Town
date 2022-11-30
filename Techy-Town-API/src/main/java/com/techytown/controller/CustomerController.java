package com.techytown.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techytown.exception.AuthenticationException;
import com.techytown.exception.CartException;
import com.techytown.exception.CustomerException;
import com.techytown.exception.ProductException;
import com.techytown.model.Cart;
import com.techytown.model.Customer;
import com.techytown.model.CustomerDTO;
import com.techytown.model.Product;
import com.techytown.services.CartService;
import com.techytown.services.CustomerService;
import com.techytown.services.LoginServices;
import com.techytown.services.ProductService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerServ;
	
	@Autowired
	private CartService cartServ;
	
	@Autowired
	private ProductService productServ;
	
	@Autowired
	private LoginServices loginService;
	
	
	@PostMapping("/customer")
	public ResponseEntity<Customer> registerCustomer(@Valid @RequestBody Customer customer){
		Customer signup = customerServ.registerCustomer(customer);
		return new ResponseEntity<Customer>(signup,HttpStatus.CREATED);
	}
	
	@PutMapping("/customer/{uuid}")
	public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer, @PathVariable(name = "uuid",required = true) String uuid) throws AuthenticationException, CustomerException{
		Customer updated = customerServ.updateCustomer(customer, uuid);
		return new ResponseEntity<Customer>(updated,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/customer/{uuid}")
	public ResponseEntity<Customer> deleteCustomer(@RequestParam String contactNo  ,@PathVariable(name = "uuid",required = true) String uuid) throws AuthenticationException, CustomerException{
		Customer deleted = customerServ.deleteCustomer(contactNo, uuid);
		loginService.logOutFromUserAccount(uuid);
		return new ResponseEntity<Customer>(deleted,HttpStatus.CREATED);
	}
	

	
	@PostMapping("/user/login")
	public ResponseEntity<String> logInUser(@Valid @RequestBody CustomerDTO udto) throws AuthenticationException {
		
		String result = loginService.logInToUserAccount(udto);

		return new ResponseEntity<String>(result,HttpStatus.OK );
		
		
	}
	
	@GetMapping("/user/logout")
	public String logoutUser(@RequestParam(required = true) String key) throws AuthenticationException {
		return loginService.logOutFromUserAccount(key);
		
	}

}
