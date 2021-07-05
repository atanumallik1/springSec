package com.springSec.springSec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

	http.authorizeRequests() // Any request must be authenticates
		.antMatchers("/", "/index").permitAll() // except these urls
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
		// .password("password") //Bare password is not good; we need password encryptpr
		// or else it will giveerror
		.password(passwordEncoder.encode("password")).roles("STUDENT").build();

	UserDetails lindaUser = User.builder().username("linda")
		// .password("password") //Bare password is not good; we need password encryptpr
		// or else it will giveerror
		.password(passwordEncoder.encode("password123")).roles("ADMIN").build();

	return new InMemoryUserDetailsManager(annasmithUser);
    }

}
