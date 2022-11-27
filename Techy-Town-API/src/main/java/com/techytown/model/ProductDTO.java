package com.techytown.model;

import lombok.Data;

@Data
public class ProductDTO {
	
	private Integer productId;
	private Integer categoryId;
	private String name;
	private String description;
	private Double mrp;
	private Double discountPrice;

}
