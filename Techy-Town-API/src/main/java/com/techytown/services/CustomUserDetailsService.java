package com.techytown.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.techytown.model.CustomUserDetail;
import com.techytown.model.User;
import com.techytown.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;
	
	public CustomUserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> user = userRepo.findUserByEmail(email);
		
//		System.out.println(email);
		
		
		if(user.isPresent()) {
//			System.out.println(user.get().getUserId());
			return new CustomUserDetail(user.get());
		}else {
			throw new UsernameNotFoundException(email);
		}
	}
	
	

}
