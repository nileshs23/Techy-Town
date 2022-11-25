package com.techytown.services;

import java.util.List;
import java.util.Set;

import com.techytown.exception.CategoryException;
import com.techytown.exception.ProductException;
import com.techytown.model.Category;
import com.techytown.model.Product;

public interface CategoryService {

	public Category addNewCategory(Category category) throws CategoryException;
	
	public Category updateCategory(Category category) throws CategoryException;
	
	public Category removeCategory(Integer catId) throws CategoryException;
	
	public Category getCategorybyId(Integer catId) throws CategoryException;
	
	public List<Product> getProductsByCategory(Integer catId) throws CategoryException,ProductException;
	
	public Set<Category> getAllCategories () throws CategoryException;
	
	public Set<String> getCategoryTypes() throws CategoryException;
}
