package com.techytown.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.techytown.model.CustomUserDetails;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	//This manages the routes
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> authz
            		.antMatchers("/","/register","/techytown/**").permitAll()
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
            		.logout((logout) -> logout
            		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            		.logoutSuccessUrl("/login")
            		.invalidateHttpSession(true)
            		.deleteCookies("JESSIONID")
            		);
        
        return http.build();
    }
//	This will be used to encrypt passwords
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	This is to configure with services
	@Bean
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(CustomUserDetailService);
	}
	
	
//	This is for ignoring your front end folders
//	@Bean
//	public void configure(WebSecurity web) throws Exception{
//		web.ignoring().antMatchers("/resources");
//	}

}
