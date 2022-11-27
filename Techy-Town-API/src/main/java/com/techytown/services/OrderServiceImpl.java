package com.techytown.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techytown.exception.UserException;
import com.techytown.model.Cart;
import com.techytown.model.User;
import com.techytown.model.Orders;
import com.techytown.repository.CartRepository;
import com.techytown.repository.OrderRepository;

import com.techytown.repository.UserRepository;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CartRepository cartRepo;

	@Override
	public Orders checkoutItems(Orders order,Integer customerId) throws UserException{
		Optional<User> custOpt = userRepo.findById(customerId);
		
		if(custOpt.isPresent()) {
			
			Optional<Cart> shoppingCartOpt =  cartRepo.findById(custOpt.get().getCart().getCartId());
			
			if(shoppingCartOpt.isPresent() 
					&& shoppingCartOpt.get().getProducts().size() >0) {
				order.setCart(shoppingCartOpt.get());
				order.getPayment().setAmt(shoppingCartOpt.get().getTotalExpenditure());
				
				return orderRepo.save(order);
			}else {
				throw new UserException("Cart Not Found For This User !");
			}
			
		}else {
			throw new UserException("No Customer Found !");
		}
	}

}
