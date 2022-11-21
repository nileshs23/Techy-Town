package com.techytown.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


import lombok.Data;

@Entity
@Data
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int paymentId;
	
	@NotNull(message = "Please Enter Amount To Pay.")
	private Double amt;
	
	@Pattern(regexp = "^[0-9]{12}$")
	private String creditCard;
	
	@NotNull(message = "You must provide Name to Order !")
	private String cardHolderName;
}
