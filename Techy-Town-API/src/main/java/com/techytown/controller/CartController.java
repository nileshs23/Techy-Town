//package com.techytown.controller;
//
//import java.util.List;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
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
//import com.techytown.exception.CartException;
//import com.techytown.exception.CustomerException;
//import com.techytown.exception.ProductException;
//import com.techytown.model.Cart;
//import com.techytown.model.Product;
//import com.techytown.services.CartService;
//import com.techytown.services.CustomerService;
//import com.techytown.services.ProductService;
//
//@RestController
//@RequestMapping("/customer")
//public class CartController {
//
//	@Autowired
//	private CustomerService customerServ;
//	
//	@Autowired
//	private CartService cartService;
//	
//	@Autowired
//	private ProductService productServ;
//	
//	@PostMapping("/customer/addProduct")
//	public ResponseEntity<Cart> addToCart(@Valid @RequestBody Product product, @RequestParam Integer customerID) throws CustomerException, ProductException, CartException{
//		
//			Cart added = cartService.addProductToCart(product, customerID);
//			return new ResponseEntity<Cart>(added,HttpStatus.CREATED);
//		
//	}
//	
//	
//	@DeleteMapping("/customer/remove")
//	public ResponseEntity<Cart> removeProduct(@RequestParam Integer productId,@RequestParam Integer customerId) throws CustomerException, ProductException, CartException{
//		
//		Cart removed = cartService.removeProductFromCart(productId, customerId);
//		return new ResponseEntity<Cart>(removed,HttpStatus.OK);
//	
//	}
//	
//	
//	@GetMapping("/customer/cartProducts")
//	public ResponseEntity<List<Product>> getProducts(@RequestParam Integer customerId) throws CustomerException, ProductException, CartException{
//		
//		List<Product> cartProducts = cartService.allCartItems(customerId);
//		
//		return new ResponseEntity<List<Product>>(cartProducts,HttpStatus.OK);
//	
//	}
//	
//	@GetMapping("/customer/cart/total")
//	public ResponseEntity<Double> totalAmount(@RequestParam Integer customerId) throws CustomerException, ProductException, CartException{
//		
//		Double totals = cartService.totalAmount(customerId);
//		
//		return new ResponseEntity<Double>(totals,HttpStatus.OK);
//	
//	}
//	
//	@GetMapping("/customer/cart/qty")
//	public ResponseEntity<Integer> quantity(@RequestParam Integer customerId) throws CustomerException, ProductException, CartException{
//		
//		Integer qty = cartService.totalQty(customerId);
//		
//		return new ResponseEntity<Integer>(qty,HttpStatus.OK);
//	
//	}
//	
//	
//}
