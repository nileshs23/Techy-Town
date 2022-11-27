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
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private CategoryRespository catRepo;
	
	@Override
	public Product saveProduct(Product product) throws ProductException {
		// TODO Auto-generated method stub
				
		Product saved = productRepo.save(product);
		
		return productRepo.saveAndFlush(saved);
		
		
//		Product saved = catRepo.findById(categoryId).map(
//				category ->{
//					product.setCategory(category);
//					return productRepo.save(product);
//				}).orElseThrow(()-> new CategoryException("Category Not found"));
//		
//		return saved;
		
	}

	@Override
	public Category getCategory(Integer productId) throws ProductException, CategoryException {
		
		Optional<Product> productOpt = productRepo.findById(productId);
		
		if(productOpt.isPresent()) {
			Category savedCat = productOpt.get().getCategory();
			if(savedCat != null) {
				return savedCat;
			}else {
				throw new CategoryException("Category Not Found !");
			}
		}else {
			throw new ProductException("Product Not Found !");
		}
		
	}

	@Override
	public Product searchProductById(Integer productId) throws ProductException {
		
		Optional<Product> productOpt =  productRepo.findById(productId);
		
		if(productOpt.isPresent()) {
			return productOpt.get();
		}else {
			throw new ProductException("Product Not Found !");
		}
		
	}

	@Override
	public Product deleteProduct(Integer productId) throws ProductException {
		
		Optional<Product> productOpt =  productRepo.findById(productId);
		
		if(productOpt.isPresent()) {
			Product deleted = productOpt.get();
			productRepo.delete(deleted);
			return deleted;
		}else {
			throw new ProductException("Product Not Found !");
		}
	}

	@Override
	public List<Product> products() throws ProductException {
		List<Product> products = productRepo.findAll();
		
		if(products.size() != 0) {
			return products;
		}else {
			throw new ProductException("Products Not Found !");
		}
	}

}
