package com.example.exception_handling.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {
	
	 private UserDetailsService userDetailsService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)  throws Exception{
		httpSecurity.csrf(csrf -> csrf.disable()).authorizeHttpRequests(
				auth -> {
				//	auth.requestMatchers(HttpMethod.POST,"/student/**").hasRole("ADMIN","USER");
				//	auth.requestMatchers(HttpMethod.GET, "/student/**").hasAnyRole("ADMIN", "USER");
				//	auth.requestMatchers(HttpMethod.GET, "/student/**").permitAll();
				//	auth.requestMatchers(HttpMethod.PUT, "/student/**").hasRole("ADMIN");
				//	auth.requestMatchers(HttpMethod.DELETE, "/student/**").hasRole("ADMIN");
				//	auth.requestMatchers(HttpMethod.PATCH, "/student/**").hasAnyRole("USER");
				//	auth.requestMatchers("/student/**").permitAll();
					auth.anyRequest().authenticated();
				}
				).httpBasic(Customizer.withDefaults());
		
		return httpSecurity.build();
	}
	   @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
	        return configuration.getAuthenticationManager();
	    }
		
//		@Bean
//		public UserDetailsService userDetailsService() {
//	
//			UserDetails ramesh = User.builder().username("akhil").password(passwordEncoder().encode("akhil"))
//					.roles("USER").build();
//	
//			UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
//					.build();
//	
//			return new InMemoryUserDetailsManager(ramesh, admin);
//		
//			
//		
//	}
		
		@Bean
		PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}

}
