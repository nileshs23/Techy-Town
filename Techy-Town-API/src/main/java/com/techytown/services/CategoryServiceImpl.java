package com.techytown.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techytown.exception.AuthenticationException;
import com.techytown.exception.CategoryException;
import com.techytown.exception.ProductException;
import com.techytown.model.Category;
import com.techytown.model.CurrentAdminSession;
import com.techytown.model.Product;
import com.techytown.repository.AdminSessionRepository;
import com.techytown.repository.CategoryRespository;

@Service
public class CategoryServiceImpl implements CategoryService {

	
	@Autowired
	private CategoryRespository catRepo;
	
	@Autowired
	private AdminSessionRepository adminSessionRepository;
	
	
	@Override
	public Category addNewCategory(Category category,String uuid) 
			throws CategoryException,AuthenticationException{
		
		Optional<CurrentAdminSession> activeSession = adminSessionRepository.findByUuid(uuid);
		
		if(activeSession.isPresent()) {
			Optional<Category> catExistsOpt = catRepo.findById(category.getCategoryId());
			
			if(catExistsOpt.isEmpty()) {
				return catRepo.save(category);
			}else {
				throw new CategoryException("Category Already Exists !");
			}
		}else {
			throw new AuthenticationException("Please Login as Admin !");
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

	@Override
	public Category updateCategory(Category category,String uuid) throws CategoryException,AuthenticationException {
		
		Optional<CurrentAdminSession> activeSession = adminSessionRepository.findByUuid(uuid);
		
		if(activeSession.isPresent()) {
			
			Optional<Category> catOpt = catRepo.findById(category.getCategoryId());
			
			if(catOpt.isPresent()) {
				return catRepo.save(catOpt.get());
			}else {
				throw new CategoryException("Category Not Found !");
			}
			
		}else {
			throw new AuthenticationException("First Login As Admin ! ");
		}
		
	}

	@Override
	public Category removeCategory(Integer catId,String uuid) throws CategoryException,AuthenticationException {
		
		Optional<CurrentAdminSession> activeSession = adminSessionRepository.findByUuid(uuid);
		
		if(activeSession.isPresent()) {
			Optional<Category> catOpt = catRepo.findById(catId);
			
			if(catOpt.isPresent()) {
				Category cat = catOpt.get();
				catRepo.delete(cat);
				return cat;
			}else {
				throw new CategoryException("Category Not Found !");
			}
		}else {
			throw new AuthenticationException("Login As Admin !");
		}
		
		
		
	}

	@Override
	public Category getCategorybyId(Integer catId) throws CategoryException {
		
		Optional<Category> catOpt = catRepo.findById(catId);
		
		if(catOpt.isPresent()) {
			return catOpt.get();
		}else {
			throw new CategoryException("Category Not Found !");
		}
		
	}

	@Override
	public Set<Category> getAllCategories() throws CategoryException {
		
		List<Category> categories = catRepo.findAll();
		
		Set<Category> set = new HashSet<>();
		
		for(Category c:categories) {
			set.add(c);
		}
		
		if(set.size() == 0) {
			throw new CategoryException("Categories Not Found !");
		}
		
		return set;
		
	}

	@Override
	public Set<String> getCategoryTypes() throws CategoryException {
		
		List<Category> categories = catRepo.findAll();
		
		Set<String> types = new HashSet<>();
		
		if(categories.size() != 0) {
			categories.forEach( (type) ->{
				types.add(type.getType());
			});
			
			return types;
		}else {
			throw new CategoryException("Category Not Found !");
		}
	}

}