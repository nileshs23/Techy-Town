package com.techytown.models;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cartId;
	
	private double totalExpenditure;	
	
	@OneToMany
	private List<Product> products = new ArrayList<>();
	
	@OneToOne(targetEntity = User.class)
	private User user;
	
	
}
