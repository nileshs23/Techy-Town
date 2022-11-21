package com.techytown.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int customerID;
	
	@NotNull(message = "Please Enter Your First Name")
	private String firstName;
	
	@NotNull(message = "Please Enter Your Las Name")
	private String lastName;
	
	private String contactNo;
	
	private String password;
	
	@JsonIgnore
	@OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
	private Cart cart;
	
	
	
}
