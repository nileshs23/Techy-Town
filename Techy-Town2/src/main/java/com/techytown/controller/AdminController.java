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

import com.techytown.exceptions.CategoryException;
import com.techytown.exceptions.ProductException;
import com.techytown.models.Category;
import com.techytown.models.Product;
import com.techytown.service.CategoryService;
import com.techytown.service.ProductService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;
	
	//category related controlls
	
	@PostMapping("/category")
	public ResponseEntity<Category> addCategory(@Valid @RequestBody Category cat) throws CategoryException{
		Category savedCat = categoryService.addNewCategory(cat);
		
		return new ResponseEntity<Category>(savedCat,HttpStatus.CREATED);
	}
	
	@PutMapping("/category")
	public ResponseEntity<Category> updateCategory(@RequestBody Category cat) throws ProductException, CategoryException{
		Category updatedCat = categoryService.updateCategory(cat);
		
		return new ResponseEntity<Category>(updatedCat,HttpStatus.OK);
	}
	
	@DeleteMapping("/category/{catId}")
	public ResponseEntity<Category> removeCategory(@PathVariable(name = "catId",required = true) Integer catID) throws ProductException, CategoryException{
		Category removedCat = categoryService.removeCategory(catID);
		
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
}
