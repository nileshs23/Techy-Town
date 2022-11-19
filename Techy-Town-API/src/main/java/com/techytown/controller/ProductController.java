package com.techytown.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techytown.exception.CategoryException;
import com.techytown.exception.ProductException;
import com.techytown.model.Category;
import com.techytown.model.Product;
import com.techytown.services.CategoryService;
import com.techytown.services.ProductService;

@RestController
public class ProductController {
	

	@Autowired
	private CategoryService catService;
	
	@Autowired
	private ProductService prodService;
	
	@PostMapping("/product")
	public ResponseEntity<Product> saveProduct(@Valid @RequestBody Product product, @RequestParam Integer categoryId) throws ProductException, CategoryException{
		Product saved = prodService.saveProduct(product, categoryId);
		
		return new ResponseEntity<Product>(saved,HttpStatus.CREATED);
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> allProducts() throws ProductException{
		List<Product> products = prodService.products();
		
		return new ResponseEntity<List<Product>>(products,HttpStatus.FOUND);
	}
	
	@GetMapping("/product/category")
	public ResponseEntity<Category> getCategoryByProduct(@RequestParam Integer productID) throws ProductException, CategoryException{
		Category cat = prodService.getCategory(productID);
		
		return new ResponseEntity<Category>(cat,HttpStatus.OK);
	}
	
	@GetMapping("/product")
	public ResponseEntity<Product> search(@RequestParam Integer productID) throws ProductException, CategoryException{
		Product product = prodService.searchProductById(productID);
		
		return new ResponseEntity<Product>(product,HttpStatus.FOUND);
	}
	
	@DeleteMapping("/product")
	public ResponseEntity<Product> delete(@RequestParam Integer productID) throws ProductException, CategoryException{
		Product product = prodService.deleteProduct(productID);
		
		return new ResponseEntity<Product>(product,HttpStatus.OK);
	}

}
