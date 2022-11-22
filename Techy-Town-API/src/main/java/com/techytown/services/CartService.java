package com.techytown.services;

import java.util.List;

import com.techytown.exception.CustomerException;
import com.techytown.model.Cart;
import com.techytown.model.Product;

public interface CartService {
	
	public Cart addProductToCart(Product products,Integer customerId) throws CustomerException;

}
