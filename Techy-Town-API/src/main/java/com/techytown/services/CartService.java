package com.techytown.services;

import java.util.List;

import com.techytown.exception.CartException;
import com.techytown.exception.UserException;
import com.techytown.exception.ProductException;
import com.techytown.model.Cart;
import com.techytown.model.Product;

public interface CartService {
	
	public Cart addProductToCart(Product products,Integer customerId) throws UserException,ProductException,CartException;
	
	public Cart removeProductFromCart(Integer ProductId, Integer customerId) throws ProductException,UserException;
	
	public List<Product> allCartItems(Integer customerId) throws CartException,UserException;

	public Double totalAmount(Integer customerId) throws UserException,CartException;
	
	public Integer totalQty (Integer customerId) throws UserException,CartException;
}
