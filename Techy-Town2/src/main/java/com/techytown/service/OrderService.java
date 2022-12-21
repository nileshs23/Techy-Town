package com.techytown.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.techytown.exceptions.CartException;
import com.techytown.exceptions.OrderException;
import com.techytown.models.OrderDTO;
import com.techytown.models.Orders;

public interface OrderService {

	public Orders checkoutItems(OrderDTO order,Long userId) throws UsernameNotFoundException,CartException;
	
	public Orders cancelOrder(Integer orderId) throws  OrderException;
}
