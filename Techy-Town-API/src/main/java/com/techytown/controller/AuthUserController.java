package com.techytown.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.techytown.exception.CategoryException;
import com.techytown.model.Category;
import com.techytown.services.CategoryService;

@Controller
public class AuthUserController {
	
	@Autowired
	private CategoryService catService;

//	@GetMapping("/categories")
//	public ResponseEntity<Set<Category>> categories() throws CategoryException{
//		Set<Category> cats = catService.getAllCategories();
//		
//		return new ResponseEntity<Set<Category>>(cats,HttpStatus.OK);
//	}
	
}
