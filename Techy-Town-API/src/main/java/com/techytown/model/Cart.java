package com.techytown.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Data
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cartId;
	
	private int productCount;
	
	private Double grandTotal;
	
	private Double actualTotal;
	
	@OneToOne
	private Customer customer;
	
	@OneToMany
	@Transient
	private List<Product> products = new ArrayList<>();
}
