package com.techytown.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int adminId;
	
	@NotNull(message = "Please Enter Your First Name")
	private String firstName;
	
	@NotNull(message = "Please Enter Your Las Name")
	private String lastName;
	
	@Column(unique = true)
	private String contactNo;
	
	private String password;

}
