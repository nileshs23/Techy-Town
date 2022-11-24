package com.techytown.services;

import com.techytown.exception.UserException;
import com.techytown.model.Orders;

public interface OrderService {

	public Orders checkoutItems(Orders order,Integer customerId) throws UserException;
}
