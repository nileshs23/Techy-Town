package com.techytown.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	
	@Transient
	@ManyToMany(mappedBy = "orders")
	private List<Product> products = new ArrayList<>();
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "payments",referencedColumnName = "paymentId")
	private Payment payment;
	
	

}
