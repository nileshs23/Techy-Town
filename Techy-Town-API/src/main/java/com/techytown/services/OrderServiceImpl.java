package com.techytown.services;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techytown.exception.CustomerException;
import com.techytown.model.Cart;
import com.techytown.model.Customer;
import com.techytown.model.Orders;
import com.techytown.model.Payment;
import com.techytown.repository.CartRepository;
import com.techytown.repository.CustomerRespository;
import com.techytown.repository.OrderRepository;
import com.techytown.repository.PaymentRepository;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private CustomerRespository customerRepo;
	
	@Autowired
	private CartRepository cartRepo;

	@Override
	public Orders checkoutItems(Orders order,Integer customerId) throws CustomerException{
		Optional<Customer> custOpt = customerRepo.findById(customerId);
		
		if(custOpt.isPresent()) {
			
			Optional<Cart> shoppingCartOpt =  cartRepo.findById(custOpt.get().getCart().getCartId());
			
			if(shoppingCartOpt.isPresent() 
					&& shoppingCartOpt.get().getProducts().size() >0) {
				order.setCart(shoppingCartOpt.get());
				order.getPayment().setAmt(shoppingCartOpt.get().getTotalExpenditure());
				
				return orderRepo.save(order);
			}else {
				throw new CustomerException("Cart Not Found For This User !");
			}
			
		}else {
			throw new CustomerException("No Customer Found !");
		}
	}

}
