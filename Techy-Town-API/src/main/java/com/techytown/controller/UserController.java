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
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.techytown.exception.CartException;
//import com.techytown.exception.CategoryException;
//import com.techytown.exception.UserException;
//import com.techytown.exception.ProductException;
//import com.techytown.model.Cart;
//import com.techytown.model.Category;
//import com.techytown.model.Orders;
//import com.techytown.model.User;
//import com.techytown.model.Product;
//import com.techytown.services.CartService;
//import com.techytown.services.OrderService;
//import com.techytown.services.UserService;
//import com.techytown.services.ProductService;
//
//@RestController
//@RequestMapping("/user")
//public class UserController {
//
//	@Autowired
//	private UserService userServ;
//	
//	@Autowired
//	private CartService cartService;
//	
////	@Autowired
//	private OrderService orderService;
//	
//	@Autowired
//	private ProductService prodService;
//	
//	
////	@GetMapping("/")
////	public ResponseEntity<String> helloUsingSecurity(){
////		return new ResponseEntity<String>("Hello Security Is Here !",HttpStatus.CREATED);
////	}
//	
//	
//	@PostMapping("/product")
//	public ResponseEntity<Product> saveProduct(@Valid @RequestBody Product product, @RequestParam Integer categoryId) throws ProductException, CategoryException{
//		Product saved = prodService.saveProduct(product, categoryId);
//		
//		return new ResponseEntity<Product>(saved,HttpStatus.CREATED);
//	}
//	
//	@GetMapping("/products")
//	public ResponseEntity<List<Product>> allProducts() throws ProductException{
//		List<Product> products = prodService.products();
//		
//		return new ResponseEntity<List<Product>>(products,HttpStatus.FOUND);
//	}
//	
//	@GetMapping("/product/category")
//	public ResponseEntity<Category> getCategoryByProduct(@RequestParam Integer productID) throws ProductException, CategoryException{
//		Category cat = prodService.getCategory(productID);
//		
//		return new ResponseEntity<Category>(cat,HttpStatus.OK);
//	}
//	
//	@GetMapping("/find?product")
//	public ResponseEntity<Product> search(@RequestParam Integer productID) throws ProductException, CategoryException{
//		Product product = prodService.searchProductById(productID);
//		
//		return new ResponseEntity<Product>(product,HttpStatus.FOUND);
//	}
//	
//	@DeleteMapping("/remoce?product")
//	public ResponseEntity<Product> delete(@RequestParam Integer productID) throws ProductException, CategoryException{
//		Product product = prodService.deleteProduct(productID);
//		
//		return new ResponseEntity<Product>(product,HttpStatus.OK);
//	}
//	
//	@PostMapping("/cart?add")
//	public ResponseEntity<Cart> addToCart(@Valid @RequestBody Product product, @RequestParam Integer customerID) throws UserException, ProductException, CartException{
//		
//			Cart added = cartService.addProductToCart(product, customerID);
//			return new ResponseEntity<Cart>(added,HttpStatus.CREATED);
//		
//	}
//	
//	
//	@DeleteMapping("/cart?remove")
//	public ResponseEntity<Cart> removeProduct(@RequestParam Integer productId,@RequestParam Integer customerId) throws UserException, ProductException, CartException{
//		
//		Cart removed = cartService.removeProductFromCart(productId, customerId);
//		return new ResponseEntity<Cart>(removed,HttpStatus.OK);
//	
//	}
//	
//	
//	@GetMapping("/customer/cartProducts")
//	public ResponseEntity<List<Product>> getProducts(@RequestParam Integer customerId) throws UserException, ProductException, CartException{
//		
//		List<Product> cartProducts = cartService.allCartItems(customerId);
//		
//		return new ResponseEntity<List<Product>>(cartProducts,HttpStatus.OK);
//	
//	}
//	
//	@GetMapping("/customer/cart/total")
//	public ResponseEntity<Double> totalAmount(@RequestParam Integer customerId) throws UserException, ProductException, CartException{
//		
//		Double totals = cartService.totalAmount(customerId);
//		
//		return new ResponseEntity<Double>(totals,HttpStatus.OK);
//	
//	}
//	
//	@GetMapping("/customer/cart/qty")
//	public ResponseEntity<Integer> quantity(@RequestParam Integer customerId) throws UserException, ProductException, CartException{
//		
//		Integer qty = cartService.totalQty(customerId);
//		
//		return new ResponseEntity<Integer>(qty,HttpStatus.OK);
//	
//	}
//	
//	@PostMapping("/order")
//	public ResponseEntity<Orders> saveOrder(@RequestBody Orders order,@RequestParam Integer custID) throws UserException{
//		Orders reported = orderService.checkoutItems(order, custID);
//		
//		return new ResponseEntity<Orders>(reported,HttpStatus.CREATED);
//	}
//
//}
