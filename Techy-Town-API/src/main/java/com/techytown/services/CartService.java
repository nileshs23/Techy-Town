package com.techytown.services;

import java.util.List;

import com.techytown.exception.CartException;
import com.techytown.exception.CustomerException;
import com.techytown.exception.ProductException;
import com.techytown.model.Cart;
import com.techytown.model.Product;

public interface CartService {
	
	public Cart addProductToCart(Product products,Integer customerId) throws CustomerException,ProductException,CartException;
	
	public Cart removeProductFromCart(Integer ProductId, Integer customerId) throws ProductException,CustomerException;
	
	public List<Product> allCartItems(Integer customerId) throws CartException,CustomerException;

	public Double totalAmount(Integer customerId) throws CustomerException,CartException;
	
	public Integer totalQty (Integer customerId) throws CustomerException,CartException;
}
