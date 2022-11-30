package com.techytown.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techytown.exception.AuthenticationException;
import com.techytown.exception.CustomerException;
import com.techytown.model.Cart;
import com.techytown.model.CurrentUserSession;
import com.techytown.model.Customer;
import com.techytown.repository.CartRepository;
import com.techytown.repository.CustomerRepository;
import com.techytown.repository.UserSessionRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private UserSessionRepository userSessionRepo;
	
	@Override
	public Customer registerCustomer(Customer customer) {
		Cart cart = cartRepo.save(new Cart());
		Customer saved = customerRepo.save(customer);
		cart.setCustomer(saved);
		saved.setCart(cart);
		
		return customerRepo.saveAndFlush(saved);
	}

	@Override
	public Customer updateCustomer(Customer customer, String uuid) 
			throws AuthenticationException, CustomerException {
		
//		if current user session is active then only update else login dirst
		Optional<CurrentUserSession> validUserSession =  userSessionRepo.findByUuid(uuid);
		
		if(validUserSession.isPresent()) {
			CurrentUserSession cus=(validUserSession.get());
			cus.getUserId();
			
			Customer cust = customerRepo.findByContactNo(customer.getContactNo());
			
			System.out.println(cust.getContactNo());
			
			if(cust != null) {
				return customerRepo.save(customer);
			}else {
				throw new CustomerException("Customer Not Found !");
			}
			
		}
		
		throw new AuthenticationException("User Not Logged In with this number");
	}
		

	@Override
	public Customer deleteCustomer(String contactNo, String uuid) throws AuthenticationException, CustomerException {
		
		Optional<CurrentUserSession> validUserSession =  userSessionRepo.findByUuid(uuid);
		
		if(validUserSession.isPresent()) {
			CurrentUserSession cus= validUserSession.get();
			cus.getUserId();
			
			Customer cust = customerRepo.findByContactNo(contactNo);
			
			if(cust != null) {
				
				 customerRepo.delete(cust);
				 return cust;
			}else {
				throw new CustomerException("Customer Not Found !");
			}
			
		}
		
		throw new AuthenticationException("User Not Logged In with this number");
	}
		

}
