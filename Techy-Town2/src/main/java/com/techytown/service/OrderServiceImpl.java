package com.techytown.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.techytown.enums.OrderStatus;
import com.techytown.exceptions.CartException;
import com.techytown.exceptions.OrderException;
import com.techytown.models.Cart;
import com.techytown.models.OrderDTO;
import com.techytown.models.Orders;
import com.techytown.models.User;
import com.techytown.repository.CardRepository;
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
	
	@Autowired
	private CardRepository cardRepo;

	@Override
	public Orders checkoutItems(OrderDTO order,Long userId) throws UsernameNotFoundException, CartException{
		Optional<User> userOpt = userRepo.findById(userId);
		
		if(userOpt.isPresent()) {
			
			Optional<Cart> shoppingCartOpt =  cartRepo.findById(userOpt.get().getCart().getCartId());
			
			if(shoppingCartOpt.isPresent() 
					&& shoppingCartOpt.get().getProducts().size() >0) {
				Orders ord = new Orders();
				
				ord.setCart(order.getItems());
				ord.setOrderingDate(LocalDateTime.now());
				ord.setDeliveryDate(LocalDateTime.now().plusSeconds(30));
				ord.setStatus(OrderStatus.ORDER_IN_PROCESS);
				
				return orderRepo.save(ord);
				
				
				
			}else {
				throw new CartException("Check Cart Once Again !");
			}
			
		}else {
			throw new UsernameNotFoundException("No Customer Found !");
		}
	}

	@Override
	public Orders cancelOrder(Integer orderId) throws OrderException {
		Optional<Orders> orderOpt = orderRepo.findById(orderId);
		if(orderOpt.isPresent()) {
			orderRepo.delete(orderOpt.get());
			return orderOpt.get();
		}else {
			throw new OrderException("Order Not Found !");
		}
	}


}
