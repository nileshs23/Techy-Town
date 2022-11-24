package com.techytown.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techytown.model.Cart;
import com.techytown.model.User;
import com.techytown.repository.CartRepository;
import com.techytown.repository.UserRespository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRespository customerRepo;
	
	@Autowired
	private CartRepository cartRepo;
	
	@Override
	public User registerUser(User user) {
		Cart cart = cartRepo.save(new Cart());
		User saved = customerRepo.save(user);
		cart.setUser(saved);
		saved.setCart(cart);
		
		return customerRepo.saveAndFlush(saved);
	}

}
