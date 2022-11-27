//package com.techytown.controller;
//
//import java.util.List;
//import java.util.Set;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.techytown.exception.CategoryException;
//import com.techytown.exception.ProductException;
//import com.techytown.model.Category;
//import com.techytown.model.Payment;
//import com.techytown.model.Product;
//import com.techytown.services.CategoryService;
//import com.techytown.services.OrderService;
//import com.techytown.services.PaymentService;
//import com.techytown.services.ProductService;
//
//@RestController
//@Controller
//@RequestMapping("/admin")
//public class AdminController {
//	
//	@Autowired
//	private CategoryService catService;
//	
//	@Autowired
//	private ProductService prodService;
//	
//	@Autowired
//	private OrderService orderService;
//	
//	@Autowired
//	private PaymentService paymentSevice;
//	
//	
//	@PostMapping("/category")
//	public ResponseEntity<Category> addCategory(@Valid @RequestBody Category cat) throws CategoryException{
//		Category savedCat = catService.addNewCategory(cat);
//		
//		return new ResponseEntity<Category>(savedCat,HttpStatus.CREATED);
//	}
//	
//	@PutMapping("/category")
//	public ResponseEntity<Category> updateCategory(@RequestBody Category cat) throws ProductException, CategoryException{
//		Category updatedCat = catService.updateCategory(cat);
//		
//		return new ResponseEntity<Category>(updatedCat,HttpStatus.OK);
//	}
//	
//	@DeleteMapping("/category")
//	public ResponseEntity<Category> removeCategory(@RequestParam Integer catID) throws ProductException, CategoryException{
//		Category removedCat = catService.removeCategory(catID);
//		
//		return new ResponseEntity<Category>(removedCat,HttpStatus.OK);
//	}
//	
//	@GetMapping("/category/{id}")
//	public ResponseEntity<Category> getCategory(@PathVariable(name = "id",required = true) Integer catID) throws ProductException, CategoryException{
//		Category cat = catService.getCategorybyId(catID);
//		
//		return new ResponseEntity<Category>(cat,HttpStatus.FOUND);
//	}
//	
//	@GetMapping("/categories")
//	public ResponseEntity<Set<Category>> categories() throws CategoryException{
//		Set<Category> cats = catService.getAllCategories();
//		
//		return new ResponseEntity<Set<Category>>(cats,HttpStatus.OK);
//	}
//	
//	@GetMapping("/category/types")
//	public ResponseEntity<Set<String>> getTotalTypes() throws CategoryException{
//		Set<String> types = catService.getCategoryTypes();
//		
//		return new ResponseEntity<Set<String>>(types,HttpStatus.OK);
//	}
//	
//	
//	@GetMapping("/category/products")
//	public ResponseEntity<List<Product>> getProductsByCategory(@RequestParam Integer catID) throws ProductException, CategoryException{
//		List<Product> products = catService.getProductsByCategory(catID);
//		
//		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
//	}
//
//	@PostMapping("/payments")
//	public ResponseEntity<List<Payment>> payments() throws ProductException, CategoryException{
//		List<Payment> payments = paymentSevice.getAllPayments();
//		
//		return new ResponseEntity<List<Payment>>(payments,HttpStatus.OK);
//	}
//
//}