package com.techytown.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.techytown.exception.UserException;
import com.techytown.model.Cart;
import com.techytown.model.Role;
import com.techytown.model.User;
import com.techytown.repository.CartRepository;
import com.techytown.repository.RoleRepository;
import com.techytown.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
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
		roles.add(roleRepo.findByName("ROLE_USER").get());
		
		user.setRoles(roles);;
		
		Cart cart = cartRepo.save(new Cart());
		
		User saved = userRepo.save(user);
		cart.setUser(saved);
		saved.setCart(cart);
		
		return userRepo.saveAndFlush(saved);
	}

	@Override
	public List<User> findAllUsers() throws UserException {
		
		List<User> users = userRepo.findAll();
		
		if(users.size() != 0) {
			return users;
		}else {
			throw new UserException("No Users Found !");
		}
	}

}
