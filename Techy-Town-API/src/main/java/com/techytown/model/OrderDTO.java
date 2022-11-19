package com.techytown.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class OrderDTO {
	
	@NotNull
	private Orders order;
	
	@NotNull
	private List<Product> products;

}
