package com.techytown.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	
	@NotNull(message = "Please Enter Your First Name")
	private String firstName;
	
	@NotNull(message = "Please Enter Your Las Name")
	private String lastName;
	
	@Email(message = "Enter valid Email !")
	@NotEmpty(message = "Enter Email ID !")
	@Column(nullable = false,unique = true)
	private String email;

	private String password;
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
	@JoinTable(name = "user_role",
	joinColumns = @JoinColumn(name="UserId",referencedColumnName = "userId"),
	inverseJoinColumns = @JoinColumn(name="roleId",referencedColumnName = "id") )
	private List<Role> roles;
	
	@JsonIgnore
	@OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
	private Cart cart;

	public User(User user) {
		super();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.roles = user.getRoles();
		this.cart = user.getCart();
	}

	
}

