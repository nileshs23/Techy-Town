package com.techytown.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.techytown.model.Role;
import com.techytown.model.User;
import com.techytown.repository.RoleRepository;
import com.techytown.repository.UserRepository;

@Component
public class googleOAuth2Handler implements AuthenticationSuccessHandler{
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	RoleRepository roleRepo;
	
	RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		OAuth2AuthenticationToken token  = (OAuth2AuthenticationToken) authentication;
		
		String email = token.getPrincipal().getAttributes().get("email").toString();
		
		if(!userRepo.findUserByEmail(email).isPresent()) {
			User user = new User();
			user.setFirstName(
					token.getPrincipal()
					.getAttributes()
					.get("given_name")
					.toString()
					);
			
			user.setLastName(
					token.getPrincipal()
					.getAttributes()
					.get("family_name")
					.toString()
					);
			
			user.setEmail(email);
			
			List<Role> roles = new ArrayList<>();
			roles.add(roleRepo.findById(2).get());
			user.setRoles(roles);
			userRepo.save(user);
			
		}
		
		redirectStrategy.sendRedirect(request, response, "/");
		
	}

}
