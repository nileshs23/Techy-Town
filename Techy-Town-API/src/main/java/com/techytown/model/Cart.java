package com.techytown.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.persistence.criteria.Order;

import lombok.Data;

@Entity
@Data
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cartId;
	
	private int productCount=0;
	
	private Double mrp=0.0;
	
	private Double discountedPrice=0.0;
	
	@ElementCollection(targetClass = Product.class)
	private List<Product> products = new ArrayList<>();
	
	@OneToOne
	private Customer customer;
	
	
}
