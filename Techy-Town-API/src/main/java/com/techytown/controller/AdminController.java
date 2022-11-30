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
import com.techytown.exception.CategoryException;
import com.techytown.exception.CustomerException;
import com.techytown.exception.ProductException;
import com.techytown.model.Admin;
import com.techytown.model.AdminDTO;
import com.techytown.model.Category;
import com.techytown.model.Orders;
import com.techytown.model.Payment;
import com.techytown.model.Product;
import com.techytown.services.AdminService;
import com.techytown.services.CategoryService;
import com.techytown.services.LoginServices;
import com.techytown.services.PaymentService;
import com.techytown.services.ProductService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private LoginServices loginServices;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private ProductService productService;
	
	
	@PostMapping("/")
	public ResponseEntity<Admin> registerAdmin(@Valid @RequestBody Admin admin){
		Admin signup = adminService.registerAdmin(admin);
		return new ResponseEntity<Admin>(signup,HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> logInAdmin(@Valid @RequestBody AdminDTO adto) throws AuthenticationException {
		
		String result = loginServices.logInToAdmin(adto);
		return new ResponseEntity<String>(result,HttpStatus.OK );

	}
	
	@PutMapping("/{uuid}")
	public ResponseEntity<Admin> updateAdminDetails(@Valid @RequestBody Admin admin, @PathVariable(name = "uuid",required = true) String uuid) throws AuthenticationException, CustomerException{
		Admin updated = adminService.updateAdmin(admin, uuid);
		return new ResponseEntity<Admin>(updated,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{uuid}")
	public ResponseEntity<Admin> deleteAdmin(@RequestParam String contactNo  ,@PathVariable(name = "uuid",required = true) String uuid) throws AuthenticationException, CustomerException{
		Admin deleted = adminService.deleteAdmin(contactNo, uuid);
		loginServices.logOutFromAdmin(uuid);
		return new ResponseEntity<Admin>(deleted,HttpStatus.OK);
	}
	
	//category related controlls
	
	@PostMapping("/category/{uuid}")
	public ResponseEntity<Category> addCategory(@Valid @RequestBody Category cat,@PathVariable(name = "uuid",required = true) String uuid) throws CategoryException, AuthenticationException{
		Category savedCat = categoryService.addNewCategory(cat,uuid);
		
		return new ResponseEntity<Category>(savedCat,HttpStatus.CREATED);
	}
	
	@PutMapping("/category/{uuid}")
	public ResponseEntity<Category> updateCategory(@PathVariable(name = "uuid",required = true) String uuid,@RequestBody Category cat) throws ProductException, CategoryException, AuthenticationException{
		Category updatedCat = categoryService.updateCategory(cat, uuid);
		
		return new ResponseEntity<Category>(updatedCat,HttpStatus.OK);
	}
	
	@DeleteMapping("/category/{catId}/{uuid}")
	public ResponseEntity<Category> removeCategory(@PathVariable(name = "uuid",required = true) String uuid,@PathVariable(name = "catId",required = true) Integer catID) throws ProductException, CategoryException, AuthenticationException{
		Category removedCat = categoryService.removeCategory(catID, uuid);
		
		return new ResponseEntity<Category>(removedCat,HttpStatus.OK);
	}
	
	@GetMapping("/category/{id}")
	public ResponseEntity<Category> getCategory(@PathVariable(name = "id",required = true) Integer catID) throws ProductException, CategoryException{
		Category cat = categoryService.getCategorybyId(catID);
		
		return new ResponseEntity<Category>(cat,HttpStatus.FOUND);
	}
	
	@GetMapping("/categories")
	public ResponseEntity<Set<Category>> categories() throws CategoryException{
		Set<Category> cats = categoryService.getAllCategories();
		
		return new ResponseEntity<Set<Category>>(cats,HttpStatus.OK);
	}
	
	@GetMapping("/category/types")
	public ResponseEntity<Set<String>> getTotalTypes() throws CategoryException{
		Set<String> types = categoryService.getCategoryTypes();
		
		return new ResponseEntity<Set<String>>(types,HttpStatus.OK);
	}
	
	
	@GetMapping("/category/products")
	public ResponseEntity<List<Product>> getProductsByCategory(@RequestParam Integer catID) throws ProductException, CategoryException{
		List<Product> products = categoryService.getProductsByCategory(catID);
		
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
	}

	// product related services
	
	@PostMapping("/product/{catId}")
	public ResponseEntity<Product> saveProduct(@Valid @RequestBody Product product, @PathVariable(value = "catId",required = true) Integer categoryId) throws ProductException, CategoryException{
		Product saved = productService.saveProduct(product, categoryId);
		
		return new ResponseEntity<Product>(saved,HttpStatus.CREATED);
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> allProducts() throws ProductException{
		List<Product> products = productService.products();
		
		return new ResponseEntity<List<Product>>(products,HttpStatus.FOUND);
	}
	
	@GetMapping("/product/category/{productId}")
	public ResponseEntity<Category> getCategoryByProduct(@PathVariable(name = "productId",required = true) Integer productID) throws ProductException, CategoryException{
		Category cat = productService.getCategory(productID);
		
		return new ResponseEntity<Category>(cat,HttpStatus.OK);
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<Product> search(@PathVariable(name = "productId",required = true) Integer productID) throws ProductException, CategoryException{
		Product product = productService.searchProductById(productID);
		
		return new ResponseEntity<Product>(product,HttpStatus.FOUND);
	}
	
	@DeleteMapping("/product/{productId}")
	public ResponseEntity<Product> delete(@PathVariable(name = "productId",required = true) Integer productID) throws ProductException, CategoryException{
		Product product = productService.deleteProduct(productID);
		
		return new ResponseEntity<Product>(product,HttpStatus.OK);
	}
	
	
	@PostMapping("/payments")
	public ResponseEntity<Integer> payments() throws ProductException, CategoryException{
		Integer payments = paymentService.getAllPayments();
		
		return new ResponseEntity<Integer>(payments,HttpStatus.OK);
	}
	
	@GetMapping("/logout")
	public ResponseEntity<String> logoutAdmin(@RequestParam(required = true) String key) throws AuthenticationException {
		return new ResponseEntity<String>(loginServices.logOutFromAdmin(key),HttpStatus.OK);	
	}

}
