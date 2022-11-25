package com.techytown.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.techytown.model.CustomUserDetails;
import com.techytown.model.User;
import com.techytown.repository.UserRespository;

@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private UserRespository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> user = userRepo.findUserByEmail(username);
		
		user.orElseThrow(()->
						new UsernameNotFoundException("User Not Found !")
						);
		
		return user.map(CustomUserDetails::new).get();
		
	}

}
