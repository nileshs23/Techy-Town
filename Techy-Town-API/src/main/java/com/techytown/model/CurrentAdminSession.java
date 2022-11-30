package com.techytown.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CurrentAdminSession {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(unique =true)
	private Integer adminId;
	
	private String uuid;
	
	private LocalDateTime logInTime;

	public CurrentAdminSession(Integer adminId, String uuid, LocalDateTime logInTime) {
		super();
		this.adminId = adminId;
		this.uuid = uuid;
		this.logInTime = logInTime;
	}
	
	
	
}
