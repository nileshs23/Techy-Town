package com.techytown.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techytown.exception.CustomerException;
import com.techytown.model.Cart;
import com.techytown.model.Customer;
import com.techytown.model.Product;
import com.techytown.services.CartService;
import com.techytown.services.CustomerService;
import com.techytown.services.ProductService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerServ;
	
	@Autowired
	private CartService cartServ;
	
	@Autowired
	private ProductService productServ;
	
	
	@PostMapping("/customer")
	public ResponseEntity<Customer> registerCustomer(@Valid @RequestBody Customer customer){
		Customer signup = customerServ.registerCustomer(customer);
		return new ResponseEntity<Customer>(signup,HttpStatus.CREATED);
	}
	
	
	@PostMapping("/customer/addProduct")
	public ResponseEntity<Cart> addToCart(@Valid @RequestBody Product product, @RequestParam Integer customerID) throws CustomerException{
		Cart added = cartServ.addProductToCart(product, customerID);
		return new ResponseEntity<Cart>(added,HttpStatus.CREATED);
	}
	
}
