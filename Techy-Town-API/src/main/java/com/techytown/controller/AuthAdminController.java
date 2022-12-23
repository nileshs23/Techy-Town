package com.techytown.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.techytown.exception.CategoryException;
import com.techytown.exception.ProductException;
import com.techytown.model.Category;
import com.techytown.model.Product;
import com.techytown.model.ProductDTO;
import com.techytown.services.CategoryService;
import com.techytown.services.ProductService;

@Controller
public class AuthAdminController {
	
	@Autowired
	private CategoryService catService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/admin")
	public String adminHome() {
		return "adminHome";
	}
	
	@GetMapping("/categories")
	public String getCategories(Model model) throws CategoryException {
		model.addAttribute("categories",catService.getAllCategories());
		return "categories";
	}
	
	@GetMapping("/categories/add")
	public String getCatAdded(Model model) {
		model.addAttribute("category",new Category());
		return "categoriesAdd";
	}
	
	@PostMapping("/categories/add")
	public String addCategories(@ModelAttribute("category") Category category) {
		
		Category cat = catService.addNewCategory(category);
		System.out.println(cat);
		
		return "redirect:/categories";
	}
	
	
	@GetMapping("/categories/delete/{id}")
	public String deleteCategories(@PathVariable Integer id) throws CategoryException {
		
		catService.removeCategory(id);
		
		return "redirect:/categories";
	}

	
	@GetMapping("/categories/update/{id}")
	public String updateCategories(@PathVariable Integer id,Model model) throws CategoryException {
		
		Category cat = catService.getCategorybyId(id);
		if(cat != null) {
			model.addAttribute("category",cat);
			return "categoriesAdd";
		}else {
			throw new CategoryException();
		}
	}
	
	//products section
	
	@GetMapping("/products")
	public String getProAdded(Model model) throws ProductException {
		model.addAttribute("products",productService.products());
		return "products";
	}
	
	@GetMapping("/products/add")
	public String addProducts(Model model) throws CategoryException {
		model.addAttribute("productDTO",new ProductDTO());
		model.addAttribute("categories",catService.getAllCategories());
		return "productsAdd";
	}
	
	@PostMapping("/products/add")
	public String addProductByCat(@ModelAttribute("productDTO") ProductDTO dto) throws  ProductException, CategoryException {
		
		Product product = new Product();

		product.setName(dto.getName());
		product.setCategory(catService.getCategorybyId(dto.getCategoryId()));
		product.setDescription(dto.getDescription());
		product.setDiscountPrice(dto.getDiscountPrice());
		product.setMrp(dto.getMrp());
		
		
		productService.saveProduct(product);
		
//		System.out.println(product.getProductId());
		
		return "redirect:/products";
	}
	
	@GetMapping("/products/delete/{id}")
	public String deleteProducts(@PathVariable int id) throws ProductException {
		
		productService.deleteProduct(id);
		
		return "redirect:/products";
	}
	
	@GetMapping("/products/update/{id}")
	public String updateProducts(Model model,@PathVariable int id) throws ProductException, CategoryException {
		
		Product product = productService.searchProductById(id);
		
		ProductDTO dto = new ProductDTO();

		dto.setProductId(product.getProductId());
		dto.setName(product.getName());
		dto.setCategoryId(product.getCategory().getCategoryId());
		dto.setDescription(product.getDescription());
		dto.setDiscountPrice(product.getDiscountPrice());
		dto.setMrp(dto.getMrp());
		
		System.out.println(dto.getName());
		
		model.addAttribute("productDTO",dto);
		model.addAttribute("categories",catService.getAllCategories());
	
		return "redirect:/products";
	}
	
}
