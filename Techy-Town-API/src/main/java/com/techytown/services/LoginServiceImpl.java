package com.techytown.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techytown.exception.AuthenticationException;
import com.techytown.model.Admin;
import com.techytown.model.AdminDTO;
import com.techytown.model.CurrentAdminSession;
import com.techytown.model.CurrentUserSession;
import com.techytown.model.Customer;
import com.techytown.model.CustomerDTO;
import com.techytown.repository.AdminRepository;
import com.techytown.repository.AdminSessionRepository;
import com.techytown.repository.CustomerRepository;
import com.techytown.repository.UserSessionRepository;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginServices {
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private UserSessionRepository userSession;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private AdminSessionRepository adminSession;
	
	

	@Override
	public String logInToUserAccount(CustomerDTO custdto) throws AuthenticationException {
		
		Customer existingUser = customerRepo.findByContactNo(custdto.getContactNo());
		
		if(existingUser == null) {
			throw new AuthenticationException("No User Found with this Mobile Number !");
		}
		
		Optional<CurrentUserSession> validUserSessionOpt = userSession.findByUserId(existingUser.getCustomerID());
		
//		System.out.println(validUserSessionOpt.get());
		
		if(validUserSessionOpt.isPresent()) {
			throw new AuthenticationException("User Already logged In with this Number !");
		}
		
		if(existingUser.getPassword().equals(custdto.getPassword())) {
			
			
			String key = RandomString.make(6);
			
			CurrentUserSession cus =  
					new CurrentUserSession(existingUser.getCustomerID(), key,LocalDateTime.now());
			
			userSession.save(cus);
			
			return cus.toString();
			
			
		}else {
			throw new AuthenticationException("Please Enter a valid password");
		}
		
	}
	

	@Override
	public String logOutFromUserAccount(String key) throws AuthenticationException {
		
		Optional<CurrentUserSession> validUserSession =  userSession.findByUuid(key);
		
		if(validUserSession.isPresent()) {
			CurrentUserSession cus=(validUserSession.get());
					userSession.delete(cus);
			return " User with Id: "+cus.getUserId()+" Logged Out Successfully !";
			
		}
		
		throw new AuthenticationException("User Not Logged In with this number");
	}


	@Override
	public String logInToAdmin(AdminDTO admindto) throws AuthenticationException {
		
		Admin existingAdmin = adminRepository.findByContactNo(admindto.getContactNo());
		
		if(existingAdmin == null) {
			throw new AuthenticationException("No Admin Found with this Mobile Number !");
		}
		
		Optional<CurrentAdminSession> validAdminSessionOpt = adminSession.findById(existingAdmin.getAdminId());
		
//		System.out.println(validUserSessionOpt.get());
		
		if(validAdminSessionOpt.isPresent()) {
			throw new AuthenticationException("Admin Already logged In with this Number !");
		}
		
		if(existingAdmin.getPassword().equals(admindto.getPassword())) {
			
			
			String key = RandomString.make(6);
			
			CurrentAdminSession adminSes =  
					new CurrentAdminSession(existingAdmin.getAdminId(), key,LocalDateTime.now());
			
			adminSession.save(adminSes);
			
			return adminSes.toString();
			
			
		}else {
			throw new AuthenticationException("Please Enter a valid password");
		}
	}


	@Override
	public String logOutFromAdmin(String key) throws AuthenticationException {

		Optional<CurrentAdminSession> validAdminSession  = adminSession.findByUuid(key);
		
		if(validAdminSession.isPresent()) {
			CurrentAdminSession cas=(validAdminSession.get());
					adminSession.delete(cas);
					
			return " Admin with Id: "+cas.getAdminId()+" Logged Out Successfully !";
			
		}
		
		throw new AuthenticationException("Admin Not Logged In with this number");
	}
	
	

}
