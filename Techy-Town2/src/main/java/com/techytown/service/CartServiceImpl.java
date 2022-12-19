package com.techytown.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.techytown.exceptions.CartException;
import com.techytown.exceptions.ProductException;
import com.techytown.models.Cart;
import com.techytown.models.CartDTO;
import com.techytown.models.User;
import com.techytown.repository.CartRepository;
import com.techytown.repository.ProductRepository;
import com.techytown.repository.UserRepository;
import com.techytown.models.Product;



@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private ProductRepository productRepo;

	@Override
	public Cart addProductToCart(CartDTO items,Long userId) 
			throws ProductException,CartException{

			Optional<User> userOpt = userRepo.findById(userId);
		
			if(userOpt.isPresent()) {
				 User user = userOpt.get();
				Optional<Cart> custCartOpt = cartRepo.findById(user.getCart().getCartId());
				Optional<Product> productOpt = productRepo.findById(items.getProductId());
				
				if(productOpt.isPresent()) {
					if(custCartOpt.isPresent()) {
						Cart custCart = custCartOpt.get();
						Product addProduct = productOpt.get();
						
						Double total =custCart.getTotalExpenditure();
						custCart.setTotalExpenditure(total+addProduct.getDiscountPrice()*items.getQuantity());
						
						//here try to add duplicate products
						custCart.getProducts().add(addProduct);
						userRepo.save(user);
						return cartRepo.saveAndFlush(custCart);
					}else {
						throw new CartException("Cart Not Found.");
					}		
					
				}else {
					throw new ProductException("Product Not Found !");
				}
			}else {
				throw new UsernameNotFoundException("User Not Found !");
			}
			

		
		
		
	}

	@Override
	public Cart removeProductFromCart(Integer ProductId,Long userId)
			throws ProductException{

		Optional<User> userOpt = userRepo.findById(userId);
		Optional<Product> prodOpt = productRepo.findById(ProductId);
		
		 if(userOpt.isPresent() && prodOpt.isPresent()) {
			 Optional<Cart> custCartOpt = cartRepo.findById(userOpt.get().getCart().getCartId());
			 List<Product>products = custCartOpt.get().getProducts();
			 
			 Double total = custCartOpt.get().getTotalExpenditure();
			 
			 if(products.contains(prodOpt.get())) {
				 products.remove(prodOpt.get());
				 custCartOpt.get().setTotalExpenditure(total -prodOpt.get().getDiscountPrice());
			 }
			 
			return cartRepo.save(custCartOpt.get());
		 }else {
			 throw new ProductException("Product Not Found !s");
		 }

		 
		
	}

	@Override
	public Double totalAmount(Long userId) throws CartException {
		
			Optional<User> userOpt = userRepo.findById(userId);
			
			if(userOpt.isPresent()) {
				Optional<Cart> cartOpt =cartRepo.findById(userOpt.get().getCart().getCartId());
				
				if(cartOpt.isPresent()) {
					
					 return cartOpt.get().getTotalExpenditure();
				}else {
					throw new CartException("Cart Not Found !");
				}
			}else {
				throw new UsernameNotFoundException("User Not Found !");
			}
		
	}


	@Override
	public List<Product> allCartItems(Long userId) {

			
		Optional<User> userOpt = userRepo.findById(userId);
		
		if(userOpt.isPresent()) {
			Optional<Cart> cartOpt =cartRepo.findById(userOpt.get().getCart().getCartId());
			
				 return cartOpt.get().getProducts();
		}else {
			throw new UsernameNotFoundException("user Not Found !");
		}
	
		
	}

}
