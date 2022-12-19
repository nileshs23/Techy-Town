package com.techytown.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Entity
@Data
public class Card {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cardId;
	
	@Pattern(regexp = "^[0-9]{12}$")
	private String cardNumber;
	
	@Pattern(regexp = "^[0-9]{3}$")
	private String cvv;
	
	@NotNull(message = "Enter Valid Expiry Date For Card")
	private LocalDate expiryDate;
	
	@NotNull(message = "You must provide Name to Order !")
	private String cardHolderName;
	
	@ManyToOne(targetEntity = User.class)
	private User user;

}
