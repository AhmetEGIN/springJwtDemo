package com.example.springJwtDemo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
	
	private final JwtAuthenticationFilter jwtAuthFilter;
	private final AuthenticationProvider authenticationProvider;
	
	// bu configuration class ı içerisinde 
	
//	CSRF (Cross Site Request Forgery) genel yapı olarak bir web sitesinin açığından faydalanarak site kullanıcılarının istekleri dışında sanki o kullanıcıymış gibi erişerek işlem yapılması sürecini içerir. 
//	Genellikle GET requestleri ve SESSION işlemlerinin doğru kontrol edilememesi durumlarındaki açıklardan saldırganların faydalanmasını sağlamaktadır.
	// authorizeHttpRequest ve devamında hangi bağlantılarda yetki gerektiği veya hangi yetkilerin gerektiğini belirtiriz
	// sessionCreationPolicy -- 
	// addFilterBefor her request in önün e TokenFilter ı belirtiriz
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
        .csrf()
        .disable()
        .authorizeHttpRequests(authorize -> authorize
        		.requestMatchers("/api/v1/demo/sayhello").hasRole("USER")
        		.requestMatchers("/api/v1/auth/**").permitAll()
        		.anyRequest()
        		.authenticated()
        		)
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

		
		
		return http.build();
	}
	
}
