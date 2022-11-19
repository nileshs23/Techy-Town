package com.techytown.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techytown.model.Payment;
import com.techytown.repository.OrderRepository;
import com.techytown.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private PaymentRepository paymentRepo;

	@Override
	public List<Payment> getAllPayments() {
		return paymentRepo.findAll();
	}
	
	

}
