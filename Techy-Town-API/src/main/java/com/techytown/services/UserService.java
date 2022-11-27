package com.techytown.services;

import java.util.List;

import com.techytown.exception.UserException;
import com.techytown.model.User;

public interface UserService {
	
	public User registerUser(User user);
	
	public List<User> findAllUsers() throws UserException;

}
