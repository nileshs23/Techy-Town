package com.techytown.models;

import java.time.LocalDate;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techytown.enums.OrderStatus;

import lombok.Data;

@Entity
@Data
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Enumerated
	private OrderStatus status = OrderStatus.ORDER_IN_PROCESS;
	
	private LocalDate orderingDate;

	private LocalDate deliveryDate;
	
	@JsonIgnore
	@OneToOne(targetEntity = Cart.class)
	private Cart cart;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "payments",referencedColumnName = "paymentId")
	private Payment payment;
	
	

}
