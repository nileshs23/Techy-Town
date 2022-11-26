package com.techytown.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.techytown.model.Cart;
import com.techytown.model.Role;
import com.techytown.model.User;
import com.techytown.repository.CartRepository;
import com.techytown.repository.RoleRepository;
import com.techytown.repository.UserRespository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRespository userRepo;
	
	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public User registerUser(User user) {
		
		String password = user.getPassword();
		user.setPassword(passwordEncoder.encode(password));
		
		List<Role> roles = new ArrayList<>();
		roles.add(roleRepo.findById(2).get());
		
		user.setRoles(roles);;
		
		Cart cart = cartRepo.save(new Cart());
		
		User saved = userRepo.save(user);
		cart.setUser(saved);
		saved.setCart(cart);
		
		return userRepo.saveAndFlush(saved);
	}

}
