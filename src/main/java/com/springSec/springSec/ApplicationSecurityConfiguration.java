package com.springSec.springSec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;

import com.springSec.configs.ApplicationUserRole;

@Configuration
@EnableWebSecurity
// Enable following annotation if we want to put authorization checks on the method level 
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	// to make it work with authorities 
    	http.csrf().disable()
    	//.csrfTokenRepository(CookieServerCsrfTokenRepository.withHttpOnlyFalse())
    	//.and()
	    .authorizeRequests() // Any request must be authenticates
		.antMatchers("/", "/index").permitAll() // except these urls
		.antMatchers("/api/**").hasRole(ApplicationUserRole.STUDENT.name())
		.anyRequest().authenticated().and().httpBasic(); // mechanism for authentication is basic

    	
    	

    }
       
    
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {

	// Following code does not use password encoder this will thorw exception ; you
	// always need to use password encoder
	// UserDetails annasmithUser =
	// User.builder().username("annasmith").password("password").roles("STUDENT").build();

	UserDetails annasmithUser = User.builder().username("annasmith")
		.password(passwordEncoder.encode("password"))
		.authorities(ApplicationUserRole.STUDENT.getGrantedAuthorities())
		.build();

	UserDetails lindaUser = User.builder().username("linda")
		.password(passwordEncoder.encode("password123"))
		.authorities(ApplicationUserRole.ADMIN.getGrantedAuthorities())
		.build();
	
	
	UserDetails tomUser = User.builder().username("tom")
			.password(passwordEncoder.encode("password123"))
			.authorities(ApplicationUserRole.ADMINTRAINEE.getGrantedAuthorities())
			.build();

	return new InMemoryUserDetailsManager(annasmithUser, lindaUser, tomUser);
    }

}
