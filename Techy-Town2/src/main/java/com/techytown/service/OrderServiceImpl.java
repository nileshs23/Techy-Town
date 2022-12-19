package com.techytown.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.techytown.exceptions.CartException;
import com.techytown.models.Cart;
import com.techytown.models.Orders;
import com.techytown.models.Payment;
import com.techytown.models.User;
import com.techytown.repository.CardRepository;
import com.techytown.repository.CartRepository;
import com.techytown.repository.OrderRepository;
import com.techytown.repository.PaymentRepository;
import com.techytown.repository.UserRepository;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private PaymentRepository paymentRepo;
	
	@Autowired
	private CardRepository cardRepo;

	@Override
	public Orders checkoutItems(Orders order,Long userId) throws UsernameNotFoundException, CartException{
		Optional<User> userOpt = userRepo.findById(userId);
		
		if(userOpt.isPresent()) {
			
			Optional<Cart> shoppingCartOpt =  cartRepo.findById(userOpt.get().getCart().getCartId());
			
			if(shoppingCartOpt.isPresent() 
					&& shoppingCartOpt.get().getProducts().size() >0) {
				order.setCart(shoppingCartOpt.get());
//				order.getPayment().setAmt(shoppingCartOpt.get().getTotalExpenditure());
				
				return orderRepo.save(order);
			}else {
				throw new CartException("Check Cart Once Again !");
			}
			
		}else {
			throw new UsernameNotFoundException("No Customer Found !");
		}
	}

	@Override
	public Orders cancelOrder(Integer orderId) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}


}
