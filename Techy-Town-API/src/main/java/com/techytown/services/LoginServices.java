package com.techytown.services;

import com.techytown.exception.AuthenticationException;
import com.techytown.model.AdminDTO;
import com.techytown.model.CustomerDTO;

public interface LoginServices {

	public String logInToUserAccount(CustomerDTO custdto) throws AuthenticationException;
	
	public String logOutFromUserAccount(String key) throws AuthenticationException;
	
	public String logInToAdmin(AdminDTO admindto) throws AuthenticationException;
	
	public String logOutFromAdmin(String key) throws AuthenticationException;
	
}
