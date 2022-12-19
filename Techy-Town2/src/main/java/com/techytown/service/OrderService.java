package com.techytown.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.techytown.exceptions.CartException;
import com.techytown.models.Orders;

public interface OrderService {

	public Orders checkoutItems(Orders order,Long userId) throws UsernameNotFoundException,CartException;
	
	public Orders cancelOrder(Integer orderId) throws UsernameNotFoundException;
}
