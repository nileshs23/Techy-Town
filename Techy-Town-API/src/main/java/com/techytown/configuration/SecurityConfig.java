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

import com.techytown.services.CustomUserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	googleOAuth2Handler googleOAuth2Handler;
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	
	//This manages the routes
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests((authz) -> authz
            		.antMatchers("/**","/resources/**","/register/**").permitAll()
//            		.antMatchers(HttpMethod.POST, "/register/**").permitAll()
            		.antMatchers("/admin/**").hasRole("ADMIN")
            		.anyRequest().authenticated()
            		)
            		.formLogin((form) -> form
            		.loginPage("/login")
            		.loginProcessingUrl("/login")
            		.failureUrl("/login?error=true")
            		.defaultSuccessUrl("/")
            		.permitAll()
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
            		);
        
        return http.build();
    }
	
//	To use in postman use this after htt[
//	.csrf().disable()
	
//	"/register","/techytown/**"
	
//	This will be used to encrypt passwords
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	This is to configure with services
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        
	     auth
	          .userDetailsService(customUserDetailsService)
	          .passwordEncoder(bCryptPasswordEncoder());
	}
	
//	private static final String[] AUTH_WHITELIST = {
//	        "/swagger-resources/**",
//	        "/swagger-ui.html",
//	        "/v2/api-docs",
//	        "/webjars/**"
//	};
	
	
//	This is for ignoring your front end folders
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/resources/**","/static/**");
	}
	



}
