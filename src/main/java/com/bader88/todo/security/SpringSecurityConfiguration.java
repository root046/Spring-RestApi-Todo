package com.bader88.todo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// disable this config and move to JWT config
//@Configuration
public class SpringSecurityConfiguration {
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//    	//1: Response to preflight request doesn't pass access control check
//		//2: basic auth
		return 
				http
					.authorizeHttpRequests(
						auth -> 
							auth
							.requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.OPTIONS, "/**")).permitAll()
							.anyRequest().authenticated()
						)
					.httpBasic(Customizer.withDefaults())
					.sessionManagement(
						session -> session.sessionCreationPolicy
						(SessionCreationPolicy.STATELESS))
						// .csrf().disable() Deprecated in SB 3.1.x
					.csrf(csrf -> csrf.disable()) // Starting from SB 3.1.x using Lambda DSL
			     // .csrf(AbstractHttpConfigurer::disable) // Starting from SB 3.1.x using Method Reference
					.build();

//################## Old Try(its work fine) ###############################		
//        http.authorizeHttpRequests(
//                auth -> auth
////                	.requestMatchers(HttpMethod.OPTIONS,"/**").permitAll() 
//                	.anyRequest().authenticated()
//                	);
//        
//        http.formLogin(withDefaults());
//
//        http.csrf().disable();
//        http.headers().frameOptions().disable();
////        ########################################
////        http.authorizeHttpRequests(
////				auth -> auth.anyRequest().authenticated()
////				);
////		2) If a request is not authenticated, a popup is shown ask to login
//		http.httpBasic(withDefaults());
//		
////		mangement session
////		http.sessionManagement(
////				session -> session.sessionCreationPolicy(
////						SessionCreationPolicy.STATELESS
////						)
////				);
//		
////		3) CSRF -> POST, PUT
//		// http.csrf().disable(); // Deprecated in SB 3.1.x
//        http.csrf(csrf -> csrf.disable()); // Starting from SB 3.1.x Using Lambda DSL
//        // OR
//        // http.csrf(AbstractHttpConfigurer::disable); // Starting from SB 3.1.x Using Method Reference
////        -----------------------
//// Add this line to enable CORS configuration
//        http.cors(withDefaults());
//        
//
//        return http.build();
    }

}
