package com.techytown.services;

import java.util.List;

import com.techytown.exception.AuthenticationException;
import com.techytown.exception.CartException;
import com.techytown.exception.CustomerException;
import com.techytown.exception.ProductException;
import com.techytown.model.Cart;
import com.techytown.model.Product;

public interface CartService {
	
	public Cart addProductToCart(Product products,String uuid) 
			throws CustomerException,ProductException,CartException,AuthenticationException;
	
	public Cart removeProductFromCart(Integer ProductId,String uuid) 
			throws ProductException,CustomerException,AuthenticationException;
	
	public List<Product> allCartItems(String uuid) throws CustomerException,AuthenticationException;

	public Double totalAmount(String uuid) throws CustomerException,CartException,AuthenticationException;
	
	public Integer totalQty (String uuid) throws CustomerException,CartException,AuthenticationException;
}
