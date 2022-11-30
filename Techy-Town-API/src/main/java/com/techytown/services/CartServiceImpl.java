package com.techytown.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techytown.exception.AuthenticationException;
import com.techytown.exception.CartException;
import com.techytown.exception.CustomerException;
import com.techytown.exception.ProductException;
import com.techytown.model.Cart;
import com.techytown.model.CurrentUserSession;
import com.techytown.model.Customer;
import com.techytown.model.Product;
import com.techytown.repository.CartRepository;
import com.techytown.repository.CustomerRepository;
import com.techytown.repository.ProductRepository;
import com.techytown.repository.UserSessionRepository;


@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private UserSessionRepository userSessionRepo;
	
	@Override
	public Cart addProductToCart(Product products,String uuid) 
			throws CustomerException,ProductException,CartException, AuthenticationException{
		
		Optional<CurrentUserSession> userActiveSession = userSessionRepo.findByUuid(uuid);
		
		if(userActiveSession.isPresent()) {
			
			Optional<Customer> customerOpt = customerRepo.findById(userActiveSession.get().getUserId());
			
			if(customerOpt.isPresent()) {
				Customer cust = customerOpt.get();
				Optional<Cart> custCartOpt = cartRepo.findById(cust.getCart().getCartId());
				Optional<Product> productOpt = productRepo.findById(products.getProductId());
				
				if(productOpt.isPresent()) {
					if(custCartOpt.isPresent()) {
						Cart custCart = custCartOpt.get();
						
						List<Product> cartProduct = custCart.getProducts();

						cartProduct.add(products);
						Double total =custCart.getTotalExpenditure();
						custCart.setTotalExpenditure(total+products.getDiscountPrice());
//						cartProduct.contains(product);

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
				throw new CustomerException("Customer Not Found !");
			}
		}else {
			throw new AuthenticationException("LogIn First !");
		}
		
		
		
	}

	@Override
	public Cart removeProductFromCart(Integer ProductId,String uuid)
			throws ProductException, CustomerException, AuthenticationException{
		
		 
		 
		 Optional<CurrentUserSession> userActiveSession = userSessionRepo.findByUuid(uuid);
			
			if(userActiveSession.isPresent()) {
				Optional<Customer> custOpt = customerRepo.findById(userActiveSession.get().getUserId());
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
			}else {
				throw new AuthenticationException("Login First");
			}
		 
		
	}

	@Override
	public Double totalAmount(String uuid) throws CustomerException,CartException, AuthenticationException {
		
		
		Optional<CurrentUserSession> userActiveSession = userSessionRepo.findByUuid(uuid);
		
		if(userActiveSession.isPresent()){
			Optional<Customer> custOpt = customerRepo.findById(userActiveSession.get().getUserId());
			
			if(custOpt.isPresent()) {
				Optional<Cart> cartOpt =cartRepo.findById(custOpt.get().getCart().getCartId());
				
				if(cartOpt.isPresent()) {
					
					 return cartOpt.get().getTotalExpenditure();
				}else {
					throw new CartException("Cart Not Found !");
				}
			}else {
				throw new CustomerException("Cusotmer Not Found !");
			}
		}else {
			throw new AuthenticationException("Login First !");
		}
		
	}

	@Override
	public Integer totalQty(String uuid) throws CustomerException,CartException, AuthenticationException {
		
		
		Optional<CurrentUserSession> userActiveSession = userSessionRepo.findByUuid(uuid);
		
		if(userActiveSession.isPresent()) {
			
			Optional<Customer> custOpt = customerRepo.findById(userActiveSession.get().getUserId());
			if(custOpt.isPresent()) {
				Optional<Cart> cartOpt =cartRepo.findById(custOpt.get().getCart().getCartId());
				
				if(cartOpt.isPresent()) {
					 return cartOpt.get().getProducts().size();
				}else {
					throw new CartException("Cart Not Found !");
				}
			}else {
				throw new CustomerException("Cusotmer Not Found !");
			}
		}else {
			throw new AuthenticationException("Login First");
		}
		
		
	}

	@Override
	public List<Product> allCartItems(String uuid) throws CustomerException, AuthenticationException {
		
		
		
		Optional<CurrentUserSession> userActiveSession = userSessionRepo.findByUuid(uuid);
		
		if(userActiveSession.isPresent()) {
			
			Optional<Customer> custOpt = customerRepo.findById(userActiveSession.get().getUserId());
			
			if(custOpt.isPresent()) {
				Optional<Cart> cartOpt =cartRepo.findById(custOpt.get().getCart().getCartId());
				
					 return cartOpt.get().getProducts();
			}else {
				throw new CustomerException("Cusotmer Not Found !");
			}
		}else {
			throw new AuthenticationException("Login First !");
		}
		
		
	}

}
