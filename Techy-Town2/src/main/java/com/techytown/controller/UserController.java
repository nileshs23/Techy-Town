package com.techytown.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techytown.exceptions.CartException;
import com.techytown.exceptions.CategoryException;
import com.techytown.exceptions.ProductException;
import com.techytown.models.Cart;
import com.techytown.models.CartDTO;
import com.techytown.models.Category;
import com.techytown.models.OrderDTO;
import com.techytown.models.Orders;
import com.techytown.models.Product;
import com.techytown.models.User;
import com.techytown.security.jwt.JwtUtils;
import com.techytown.service.CartService;
import com.techytown.service.CategoryService;
import com.techytown.service.OrderService;
import com.techytown.service.ProductService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {


	@Autowired
	private CartService cartService;
	
	@Autowired
	private ProductService productService;
	
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private JwtUtils jwtUtils;

	@GetMapping("/all")
	  public String allAccess() {
	    return "Public Content.";
	  }
	
	@GetMapping("/")
	  public String getUsername(HttpServletRequest request) {
		
		String cookie = jwtUtils.getJwtFromCookies(request);
		String username = jwtUtils.getUserNameFromJwtToken(cookie);
	    return username;
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
	@GetMapping("/category")
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
	
	@GetMapping("/cart")
	public ResponseEntity<List<Product>> getProducts(HttpServletRequest request){
		
		String cookie = jwtUtils.getJwtFromCookies(request);
		String username = jwtUtils.getUserNameFromJwtToken(cookie);
		List<Product> cartProducts = cartService.allCartItems(username);
		
		return new ResponseEntity<List<Product>>(cartProducts,HttpStatus.OK);
	
	}
	
	@PostMapping("/cart/addProduct")
	public ResponseEntity<Cart> addToCart(@Valid @RequestBody CartDTO dto,HttpServletRequest request) throws ProductException, CartException{
		System.out.println("add2cart controller");
		
		String cookie = jwtUtils.getJwtFromCookies(request);
		String username = jwtUtils.getUserNameFromJwtToken(cookie);
		
		
		Cart added = cartService.addProductToCart(dto, username);
		return new ResponseEntity<Cart>(added,HttpStatus.CREATED);
		
	}
	
	
	@DeleteMapping("/cart/removeProduct")
	public ResponseEntity<Cart> removeProduct(@RequestParam Integer productId,HttpServletRequest request) throws  ProductException, CartException{
		String cookie = jwtUtils.getJwtFromCookies(request);
		String username = jwtUtils.getUserNameFromJwtToken(cookie);
		
		Cart removed = cartService.removeProductFromCart(productId, username);
		return new ResponseEntity<Cart>(removed,HttpStatus.OK);
	
	}
	
	
	
	
	@GetMapping("/cart/total")
	public ResponseEntity<Double> totalAmount(HttpServletRequest request) throws CartException{
		
		String cookie = jwtUtils.getJwtFromCookies(request);
		String username = jwtUtils.getUserNameFromJwtToken(cookie);
		
		Double totals = cartService.totalAmount(username);
		
		return new ResponseEntity<Double>(totals,HttpStatus.OK);
	
	}
	
	@GetMapping("/cart/items")
	public ResponseEntity<List<Product>> items(HttpServletRequest request) throws CartException{
		
		String cookie = jwtUtils.getJwtFromCookies(request);
		String username = jwtUtils.getUserNameFromJwtToken(cookie);
		
		List<Product> items = cartService.allCartItems(username);
		
		return new ResponseEntity<List<Product>>(items,HttpStatus.OK);
	
	}

	
	
	
	//now he can order it as he saved all cart
	
	@PostMapping("/order")
	public ResponseEntity<Orders> saveOrder(@RequestBody OrderDTO dto,@RequestParam Long userId) throws UsernameNotFoundException, CartException{
		Orders reported = orderService.checkoutItems(dto, userId);
		
		return new ResponseEntity<Orders>(reported,HttpStatus.CREATED);
	}
	

	
}
