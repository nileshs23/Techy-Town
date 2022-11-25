package com.techytown.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.techytown.model.CustomUserDetails;
import com.techytown.services.CustomUserDetailService;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	googleOAuth2Handler googleOAuth2Handler;
	
	@Autowired
	CustomUserDetailService customUserDetailService;
	
	
	//This manages the routes
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> authz
            		.antMatchers("/","/register","/techytown/**",
            				"/swagger-ui/**","/v3/api-docs/**",
                    		"/swagger-ui.html").permitAll()
            		.antMatchers("/admin/**").hasRole("ADMIN")
            		.anyRequest().authenticated()
            		)
            		.formLogin((form) -> form
            		.loginPage("/login").permitAll()
            		.failureUrl("/login?error=true")
            		.defaultSuccessUrl("/")
            		.usernameParameter("email")
            		.passwordParameter("password")
            		)
            		.oauth2Login((google)->google
            				.loginPage("/login")
            				.successHandler(googleOAuth2Handler)
            		)
            		.logout((logout) -> logout
            		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            		.logoutSuccessUrl("/login")
            		.invalidateHttpSession(true)
            		.deleteCookies("JESSIONID")
            		)
            		.exceptionHandling(e->e
            				.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
            		)
            		;
        
        return http.build();
    }
//	This will be used to encrypt passwords
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	This is to configure with services
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(customUserDetailService);
	}
	
	
	
	
//	This is for ignoring your front end folders
//	@Bean
//	public void configure(WebSecurity web) throws Exception{
//		web.ignoring().antMatchers("/resources");
//	}
	



}
