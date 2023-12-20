package com.bader88.todo.security;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SpringSecurityConfiguration {
    //LDAP or Database
    //In Memory

    //InMemoryUserDetailsManager
    //InMemoryUserDetailsManager(UserDetails... users)

    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager() {

        UserDetails userDetails1 = createNewUser("root", "0000");
        UserDetails userDetails2 = createNewUser("x", "x");

        return new InMemoryUserDetailsManager(userDetails1, userDetails2);
    }

    private UserDetails createNewUser(String username, String password) {
        Function<String, String> passwordEncoder
                = input -> passwordEncoder().encode(input);

        UserDetails userDetails = User.builder()
                .passwordEncoder(passwordEncoder)
                .username(username)
                .password(password)
                .roles("USER","ADMIN")
                .build();
        return userDetails;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated());
        http.formLogin(withDefaults());

        http.csrf().disable();
        http.headers().frameOptions().disable();
//        ########################################
//        http.authorizeHttpRequests(
//				auth -> auth.anyRequest().authenticated()
//				);
//		2) If a request is not authenticated, a web page is shown
		http.httpBasic(withDefaults());
		
//		3) CSRF -> POST, PUT
		// http.csrf().disable(); // Deprecated in SB 3.1.x
        http.csrf(csrf -> csrf.disable()); // Starting from SB 3.1.x Using Lambda DSL
        // OR
        // http.csrf(AbstractHttpConfigurer::disable); // Starting from SB 3.1.x Using Method Reference
//        -----------------------
// Add this line to enable CORS configuration
        http.cors(withDefaults());
        

        return http.build();
    }
    

}