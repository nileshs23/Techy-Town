package com.techytown.model;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@Table(name = "products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productId;
	
	private String name;
	
	@Size(min=0,max=255,message = "Describe your product in 255 characters !")
	private String description;
	
	@NotNull(message = "Please Enter Max Retail Price.")
	private Double mrp;
	
	@NotNull(message = "Please Enter Discounted Price.")
	private Double discountPrice;
	
//	@NotNull(message = "Provide IMG url !")
//	private String img;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Category category;
	
	
}
