package com.techytown.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techytown.model.Cart;
import com.techytown.model.Customer;
import com.techytown.repository.CartRepository;
import com.techytown.repository.CustomerRespository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRespository customerRepo;
	
	@Autowired
	private CartRepository cartRepo;
	
	@Override
	public Customer registerCustomer(Customer customer) {
		Cart cart = cartRepo.save(new Cart());
		Customer saved = customerRepo.save(customer);
		cart.setCustomer(saved);
		saved.setCart(cart);
		
		return customerRepo.saveAndFlush(saved);
	}

}
