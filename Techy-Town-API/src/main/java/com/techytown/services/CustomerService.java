package com.techytown.services;

import com.techytown.exception.AuthenticationException;
import com.techytown.exception.CustomerException;
import com.techytown.model.Customer;

public interface CustomerService {
	
	public Customer registerCustomer(Customer  customer);
	
	public Customer updateCustomer(Customer customer,String uuid) throws AuthenticationException,CustomerException;
	
	public Customer deleteCustomer(String contactNo,String uuid) throws AuthenticationException,CustomerException;

}
