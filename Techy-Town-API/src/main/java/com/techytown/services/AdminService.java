package com.techytown.services;

import com.techytown.exception.AuthenticationException;
import com.techytown.exception.CustomerException;
import com.techytown.model.Admin;

public interface AdminService {
	
	public Admin registerAdmin(Admin admin);
	
	public Admin updateAdmin(Admin admin,String uuid) throws AuthenticationException,CustomerException;
	
	public Admin deleteAdmin(String contactNo,String uuid) throws AuthenticationException,CustomerException;
}
