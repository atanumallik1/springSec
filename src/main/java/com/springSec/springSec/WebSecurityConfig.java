package com.springSec.springSec;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration 
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
	// step 1: Permit all
//	http.authorizeRequests().antMatchers("/").permitAll() ;

	// step 2: Permit "/" , suthorize /hello : using basic authentocation : can be
	// tested in postman
//	http.authorizeRequests().antMatchers("/").permitAll().anyRequest().authenticated().and().httpBasic();

	// step 3: Permit "/" , suthorize /hello : using Form based authentication : can
	// be
	// tested in browser
//	http.authorizeRequests().antMatchers("/").permitAll().anyRequest().authenticated().and().formLogin() ;

	// step 4: Permit "/" , suthorize /hello : using Form based + basic
	// authentication : can be
	// tested in browser
	http.authorizeRequests().antMatchers("/").permitAll().anyRequest().authenticated().and().formLogin().and()
		.httpBasic();

    }

}
