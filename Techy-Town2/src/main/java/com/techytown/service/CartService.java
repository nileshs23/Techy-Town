package com.techytown.service;


import java.util.List;

import com.techytown.exceptions.CartException;
import com.techytown.exceptions.ProductException;
import com.techytown.models.Cart;
import com.techytown.models.CartDTO;
import com.techytown.models.Product;

public interface CartService {
	
	public Cart addProductToCart(CartDTO items,String username) 
			throws ProductException,CartException;
	
	public Cart removeProductFromCart(Integer ProductId,String username) 
			throws ProductException,CartException;
	
	public List<Product> allCartItems(String username);

	public Double totalAmount(String username) throws CartException;
}
