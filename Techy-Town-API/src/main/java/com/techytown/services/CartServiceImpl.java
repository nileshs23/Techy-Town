package com.techytown.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techytown.exception.CustomerException;
import com.techytown.model.Cart;
import com.techytown.model.Customer;
import com.techytown.model.Product;
import com.techytown.repository.CartRepository;
import com.techytown.repository.CustomerRespository;
import com.techytown.repository.ProductRepository;


@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CustomerRespository customerRepo;
	
	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Override
	public Cart addProductToCart(Product products, Integer customerId) throws CustomerException{
		
		Optional<Customer> customerOpt = customerRepo.findById(customerId);
		
		if(customerOpt.isPresent()) {
			Customer cust = customerOpt.get();
			Optional<Cart> custCartOpt = cartRepo.findById(cust.getCart().getCartId());
			Optional<Product> productOpt = productRepo.findById(products.getProductId());
			
			if(custCartOpt.isPresent() && productOpt.isPresent()) {
				Cart custCart = custCartOpt.get();
				Product product = productOpt.get();
				
				Double actual = product.getMrp();
				Double discounted = product.getDiscountPrice();
				
				List<Product> cartProduct = custCart.getProducts();
				
				cartProduct.add(products);
				
				custCart.setProductCount(cartProduct.size());
				custCart.setDiscountedPrice(custCart.getDiscountedPrice()+discounted);
				custCart.setMrp(custCart.getMrp()+actual);
				
				//here try to add duplicate products 
				
				customerRepo.save(cust);
				return cartRepo.saveAndFlush(custCart);
					
				
			}else {
				throw new CustomerException("Cart or Product Not Found !");
			}
		}else {
			throw new CustomerException("Customer Not Found !");
		}
		
		
	}

}
