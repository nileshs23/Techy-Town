package com.techytown.services;

import java.util.List;

import com.techytown.exception.CategoryException;
import com.techytown.exception.ProductException;
import com.techytown.model.Category;
import com.techytown.model.Product;

public interface ProductService {
	
	public Product saveProduct(Product product) throws ProductException;

	public Category getCategory(Integer productId) throws ProductException,CategoryException;
	
	public Product searchProductById(Integer productId) throws ProductException;
	
	public Product deleteProduct(Integer productId) throws ProductException;
	
	public List<Product> products() throws ProductException;
}
