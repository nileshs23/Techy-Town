package com.techytown.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techytown.exception.CategoryException;
import com.techytown.exception.ProductException;
import com.techytown.model.Category;
import com.techytown.model.Product;
import com.techytown.repository.CategoryRespository;
import com.techytown.repository.ProductRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	
	@Autowired
	private CategoryRespository catRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Override
	public Category addNewCategory(Category category) throws CategoryException {
		
		Optional<Category> catExistsOpt = catRepo.findById(category.getCategoryId());
		
		if(catExistsOpt.isEmpty()) {
			return catRepo.save(category);
		}else {
			throw new CategoryException("Category Already Exists !");
		}
	}

	@Override
	public List<Product> getProductsByCategory(Integer catId) throws CategoryException, ProductException {
		Optional<Category> catOpt = catRepo.findById(catId);
		
		if(catOpt.isPresent()) {
			List<Product> products =  catOpt.get().getProducts();
			
			if(products.size() != 0) {
				return products;
			}else {
				throw new ProductException("No Product Found For This Category !");
			}
		}else {
			throw new CategoryException("Category Not Found !");
		}
	}

}
