package com.techytown.services;

import com.techytown.exception.CustomerException;
import com.techytown.model.Orders;

public interface OrderService {

	public Orders checkoutItems(Orders order,Integer customerId) throws CustomerException;
}
