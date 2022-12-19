package com.techytown.service;


import java.util.List;

import com.techytown.exceptions.CartException;
import com.techytown.exceptions.ProductException;
import com.techytown.models.Cart;
import com.techytown.models.CartDTO;
import com.techytown.models.Product;

public interface CartService {
	
	public Cart addProductToCart(CartDTO items,Long userID) 
			throws ProductException,CartException;
	
	public Cart removeProductFromCart(Integer ProductId,Long userId) 
			throws ProductException;
	
	public List<Product> allCartItems(Long userId);

	public Double totalAmount(Long userId) throws CartException;
}
