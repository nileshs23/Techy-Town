package com.techytown.controller;

import java.util.List;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techytown.exception.AuthenticationException;
import com.techytown.exception.CartException;
import com.techytown.exception.CategoryException;
import com.techytown.exception.CustomerException;
import com.techytown.exception.ProductException;
import com.techytown.model.Cart;
import com.techytown.model.Category;
import com.techytown.model.CurrentUserSession;
import com.techytown.model.Customer;
import com.techytown.model.CustomerDTO;
import com.techytown.model.Orders;
import com.techytown.model.Payment;
import com.techytown.model.Product;
import com.techytown.services.CartService;
import com.techytown.services.CategoryService;
import com.techytown.services.CustomerService;
import com.techytown.services.LoginServices;
import com.techytown.services.OrderService;
import com.techytown.services.PaymentService;
import com.techytown.services.ProductService;

@RestController
@RequestMapping("/user")
public class CustomerController {

	@Autowired
	private CustomerService customerServ;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private ProductService productService;
	
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private LoginServices loginService;
	
	
	@PostMapping("/")
	public ResponseEntity<Customer> registerCustomer(@Valid @RequestBody Customer customer){
		Customer signup = customerServ.registerCustomer(customer);
		return new ResponseEntity<Customer>(signup,HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> logInUser(@Valid @RequestBody CustomerDTO udto) throws AuthenticationException {
		
		String result = loginService.logInToUserAccount(udto);

		return new ResponseEntity<String>(result,HttpStatus.OK );
		
		
	}
	
	@PutMapping("/{uuid}")
	public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer, @PathVariable(name = "uuid",required = true) String uuid) throws AuthenticationException, CustomerException{
		Customer updated = customerServ.updateCustomer(customer, uuid);
		return new ResponseEntity<Customer>(updated,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{uuid}")
	public ResponseEntity<Customer> deleteCustomer(@RequestParam String contactNo  ,@PathVariable(name = "uuid",required = true) String uuid) throws AuthenticationException, CustomerException{
		Customer deleted = customerServ.deleteCustomer(contactNo, uuid);
		loginService.logOutFromUserAccount(uuid);
		return new ResponseEntity<Customer>(deleted,HttpStatus.CREATED);
	}
	
	//product controller menu
	
	//search the product
	@GetMapping("/product/{productId}")
	public ResponseEntity<Product> searchProductById(@PathVariable(name = "productId",required = true) Integer productID) throws ProductException, CategoryException{
		Product product = productService.searchProductById(productID);
		
		return new ResponseEntity<Product>(product,HttpStatus.FOUND);
	}
	
	//get products by category
	@GetMapping("/{catId}/products")
	public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable(name = "catId",required = true) Integer catID) throws ProductException, CategoryException{
		List<Product> products = categoryService.getProductsByCategory(catID);
		
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
	}
	
	//by product get category
	@GetMapping("/{productId}/category")
	public ResponseEntity<Category> getCategoryByProduct(@PathVariable(name = "productId",required = true) Integer productID) throws ProductException, CategoryException{
		Category cat = productService.getCategory(productID);
		
		return new ResponseEntity<Category>(cat,HttpStatus.OK);
	}
	
	//see categories list
	@GetMapping("/categories")
	public ResponseEntity<Set<Category>> categories() throws CategoryException{
		Set<Category> cats = categoryService.getAllCategories();
		
		return new ResponseEntity<Set<Category>>(cats,HttpStatus.OK);
	}
	
	//get particular category
	@GetMapping("/category/{id}")
	public ResponseEntity<Category> getCategory(@PathVariable(name = "id",required = true) Integer catID) throws ProductException, CategoryException{
		Category cat = categoryService.getCategorybyId(catID);
		
		return new ResponseEntity<Category>(cat,HttpStatus.FOUND);
	}
	
	
	
	//cart menu and order it
	
	//add the product no duplicates are allowed
	
	@GetMapping("/cart/{uuid}")
	public ResponseEntity<List<Product>> getProducts(@PathVariable(name = "uuid",required = true) String uuid) throws CustomerException, AuthenticationException{
		
		
		List<Product> cartProducts = cartService.allCartItems(uuid);
		
		return new ResponseEntity<List<Product>>(cartProducts,HttpStatus.OK);
	
	}
	
	@PostMapping("/cart/addProduct/{uuid}")
	public ResponseEntity<Cart> addToCart(@Valid @RequestBody Product product, @PathVariable(name = "uuid",required = true) String uuid) throws CustomerException, ProductException, CartException, AuthenticationException{
		
			Cart added = cartService.addProductToCart(product, uuid);
			return new ResponseEntity<Cart>(added,HttpStatus.CREATED);
		
	}
	
	
	@DeleteMapping("/cart/removeProduct/{uuid}")
	public ResponseEntity<Cart> removeProduct(@RequestParam Integer productId, @PathVariable(name = "uuid",required = true) String uuid) throws CustomerException, ProductException, CartException, AuthenticationException{
		
		Cart removed = cartService.removeProductFromCart(productId, uuid);
		return new ResponseEntity<Cart>(removed,HttpStatus.OK);
	
	}
	
	
	
	
	@GetMapping("/cart/total/{uuid}")
	public ResponseEntity<Double> totalAmount(@PathVariable(name = "uuid",required = true) String uuid) throws CustomerException, ProductException, CartException, AuthenticationException{
		
		Double totals = cartService.totalAmount(uuid);
		
		return new ResponseEntity<Double>(totals,HttpStatus.OK);
	
	}
	
	@GetMapping("/cart/qty/{uuid}")
	public ResponseEntity<Integer> quantity(@PathVariable(name = "uuid",required = true) String uuid) throws CustomerException, ProductException, CartException, AuthenticationException{
		
		Integer qty = cartService.totalQty(uuid);
		
		return new ResponseEntity<Integer>(qty,HttpStatus.OK);
	
	}
	
	
	
	//now he can order it as he saved all cart
	
	@PostMapping("/order")
	public ResponseEntity<Orders> saveOrder(@RequestBody Orders order,@RequestParam Integer custID) throws CustomerException{
		Orders reported = orderService.checkoutItems(order, custID);
		
		return new ResponseEntity<Orders>(reported,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/logout")
	public ResponseEntity<String> logoutUser(@RequestParam(required = true) String key) throws AuthenticationException {
		 
		
		return new ResponseEntity<String>(loginService.logOutFromUserAccount(key),HttpStatus.OK);
		
	}

}
