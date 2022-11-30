package com.techytown.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techytown.exception.AuthenticationException;
import com.techytown.exception.CustomerException;
import com.techytown.model.Admin;
import com.techytown.model.CurrentAdminSession;
import com.techytown.repository.AdminRepository;
import com.techytown.repository.AdminSessionRepository;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private AdminSessionRepository adminSessionRepository;

	@Override
	public Admin registerAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return adminRepository.save(admin);
	}

	@Override
	public Admin updateAdmin(Admin admin, String uuid) throws AuthenticationException, CustomerException {
		//if current admin session is active then only update else login first
		
		Optional<CurrentAdminSession> validAdminSession =  adminSessionRepository.findByUuid(uuid);
		
		if(validAdminSession.isPresent()) {
			CurrentAdminSession cas=(validAdminSession.get());
//			cas.getUserId();
			
			Admin savedAdmin = adminRepository.findByContactNo(admin.getContactNo());
			
//			System.out.println(savedAdmin.getContactNo());
			
			if(savedAdmin != null) {
				return adminRepository.save(admin);
			}else {
				throw new CustomerException("ADMIN Not Found!");
			}
			
		}
		
		throw new AuthenticationException("Admin is Not Logged In with this number");
	}

	@Override
	public Admin deleteAdmin(String contactNo, String uuid) throws AuthenticationException, CustomerException {
		// TODO Auto-generated method stub
		
		Optional<CurrentAdminSession> validAdminSession =  adminSessionRepository.findByUuid(uuid);
		
		if(validAdminSession.isPresent()) {
//			CurrentAdminSession cas=validAdminSession.get();
			
			Admin savedAdmin = adminRepository.findByContactNo(contactNo);
			
//			System.out.println(savedAdmin.getContactNo());
			
			if(savedAdmin != null) {
				adminRepository.delete(savedAdmin);
				return savedAdmin;
			}else {
				throw new CustomerException("ADMIN Not Found!");
			}
			
		}
		
		throw new AuthenticationException("Admin is Not Logged In with this number");
	}
	
	

}
