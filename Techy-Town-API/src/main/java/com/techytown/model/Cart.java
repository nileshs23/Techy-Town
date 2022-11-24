package com.techytown.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cartId;
	
	@ElementCollection(targetClass = Product.class)
	private List<Product> products = new ArrayList<>();
	
	private double totalExpenditure;
	
	@OneToOne
	private User user;
	
	
}
