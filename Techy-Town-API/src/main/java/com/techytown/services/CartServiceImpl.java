package com.techytown.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techytown.exception.CartException;
import com.techytown.exception.UserException;
import com.techytown.exception.ProductException;
import com.techytown.model.Cart;
import com.techytown.model.User;
import com.techytown.model.Product;
import com.techytown.repository.CartRepository;
import com.techytown.repository.UserRepository;
import com.techytown.repository.ProductRepository;


@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private UserRepository customerRepo;
	
	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Override
	public Cart addProductToCart(Product products, Integer customerId) throws UserException,ProductException,CartException{
		
		Optional<User> customerOpt = customerRepo.findById(customerId);
		
		if(customerOpt.isPresent()) {
			User cust = customerOpt.get();
			Optional<Cart> custCartOpt = cartRepo.findById(cust.getCart().getCartId());
			Optional<Product> productOpt = productRepo.findById(products.getProductId());
			
			if(productOpt.isPresent()) {
				if(custCartOpt.isPresent()) {
					Cart custCart = custCartOpt.get();
					
					List<Product> cartProduct = custCart.getProducts();

					cartProduct.add(products);
					Double total =custCart.getTotalExpenditure();
					custCart.setTotalExpenditure(total+products.getDiscountPrice());
//					cartProduct.contains(product);

					//here try to add duplicate products
					
					customerRepo.save(cust);
					return cartRepo.saveAndFlush(custCart);
				}else {
					throw new CartException("Cart Not Found.");
				}		
				
			}else {
				throw new ProductException("Product Not Found !");
			}
		}else {
			throw new UserException("Customer Not Found !");
		}
		
		
	}

	@Override
	public Cart removeProductFromCart(Integer ProductId, Integer customerId)
			throws ProductException, UserException{
		 Optional<User> custOpt = customerRepo.findById(customerId);
		 Optional<Product> prodOpt = productRepo.findById(ProductId);
		 
		 if(custOpt.isPresent() && prodOpt.isPresent()) {
			 Optional<Cart> custCartOpt = cartRepo.findById(custOpt.get().getCart().getCartId());
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
	public Double totalAmount(Integer customerId) throws UserException,CartException {
		Optional<User> custOpt = customerRepo.findById(customerId);
		
		if(custOpt.isPresent()) {
			Optional<Cart> cartOpt =cartRepo.findById(custOpt.get().getCart().getCartId());
			
			if(cartOpt.isPresent()) {
				
				 return cartOpt.get().getTotalExpenditure();
			}else {
				throw new CartException("Cart Not Found !");
			}
		}else {
			throw new UserException("Cusotmer Not Found !");
		}
	}

	@Override
	public Integer totalQty(Integer customerId) throws UserException,CartException {
		
		Optional<User> custOpt = customerRepo.findById(customerId);
		
		if(custOpt.isPresent()) {
			Optional<Cart> cartOpt =cartRepo.findById(custOpt.get().getCart().getCartId());
			
			if(cartOpt.isPresent()) {
				 return cartOpt.get().getProducts().size();
			}else {
				throw new CartException("Cart Not Found !");
			}
		}else {
			throw new UserException("Cusotmer Not Found !");
		}
	}

	@Override
	public List<Product> allCartItems(Integer customerId) throws CartException, UserException {
		
		Optional<User> custOpt = customerRepo.findById(customerId);
		
		if(custOpt.isPresent()) {
			Optional<Cart> cartOpt =cartRepo.findById(custOpt.get().getCart().getCartId());
			
			if(cartOpt.isPresent()) {
				 return cartOpt.get().getProducts();
			}else {
				throw new CartException("Cart Not Found !");
			}
		}else {
			throw new UserException("Cusotmer Not Found !");
		}
	}

}
