package com.techytown.services;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techytown.model.Orders;
import com.techytown.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepo;
	
//	@Autowired
//	private PaymentRepository paymentRepo;

	@Override
	public Orders addOrder(Orders order) {
		Orders savedOrder = orderRepo.save(order);
		savedOrder.setDeliveryDate(LocalDate.now().plusDays(3));
		
		return savedOrder;
	}

}
