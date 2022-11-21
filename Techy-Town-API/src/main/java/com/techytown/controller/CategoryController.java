package com.techytown.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techytown.exception.CategoryException;
import com.techytown.exception.CustomerException;
import com.techytown.exception.ProductException;
import com.techytown.model.Category;
import com.techytown.model.Orders;
import com.techytown.model.Payment;
import com.techytown.model.Product;
import com.techytown.services.CategoryService;
import com.techytown.services.OrderService;
import com.techytown.services.PaymentService;
import com.techytown.services.ProductService;

@RestController
public class CategoryController {

	@Autowired
	private CategoryService catService;
	
	@Autowired
	private ProductService prodService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private PaymentService paymentSevice;
	
	
	@PostMapping("/category")
	public ResponseEntity<Category> saveCategory(@Valid @RequestBody Category cat) throws CategoryException{
		Category savedCat = catService.addNewCategory(cat);
		
		return new ResponseEntity<Category>(savedCat,HttpStatus.CREATED);
	}
	
	@GetMapping("/category/product")
	public ResponseEntity<List<Product>> getProductsByCategory(@RequestParam Integer catID) throws ProductException, CategoryException{
		List<Product> products = catService.getProductsByCategory(catID);
		
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
	}
	
	
	@PostMapping("/order")
	public ResponseEntity<Orders> saveOrder(@RequestBody Orders order,@RequestParam Integer custID) throws CustomerException{
		Orders reported = orderService.checkoutItems(order, custID);
		
		return new ResponseEntity<Orders>(reported,HttpStatus.CREATED);
	}
	
	@PostMapping("/payments")
	public ResponseEntity<List<Payment>> payments() throws ProductException, CategoryException{
		List<Payment> payments = paymentSevice.getAllPayments();
		
		return new ResponseEntity<List<Payment>>(payments,HttpStatus.OK);
	}
	
}
