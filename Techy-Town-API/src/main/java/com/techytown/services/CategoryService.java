package com.techytown.services;

import java.util.List;

import com.techytown.exception.CategoryException;
import com.techytown.exception.ProductException;
import com.techytown.model.Category;
import com.techytown.model.Product;

public interface CategoryService {

	public Category addNewCategory(Category category) throws CategoryException;
	
	public List<Product> getProductsByCategory(Integer catId) throws CategoryException,ProductException;
}
