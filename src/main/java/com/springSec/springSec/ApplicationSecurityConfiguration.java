package com.springSec.springSec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.springSec.configs.ApplicationUserPermissions;
import com.springSec.configs.ApplicationUserRole;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
/*
	http.csrf().disable() //TODO :Never do this
	    .authorizeRequests() // Any request must be authenticates
		.antMatchers("/", "/index").permitAll() // except these urls
		.antMatchers("/api/**").hasRole(ApplicationUserRole.STUDENT.name())
		.antMatchers(HttpMethod.PUT ,"/management/api/v1/**").hasAnyAuthority(ApplicationUserPermissions.COURSE_WRITE.name())
		.antMatchers(HttpMethod.POST ,"/management/api/v1/**").hasAnyAuthority(ApplicationUserPermissions.COURSE_WRITE.name())
		.antMatchers(HttpMethod.DELETE,"/management/api/v1/**").hasAnyAuthority(ApplicationUserPermissions.COURSE_WRITE.name())
		.antMatchers(HttpMethod.GET ,"/management/api/v1/**").hasAnyRole(ApplicationUserRole.ADMIN.name(), ApplicationUserRole.ADMINTRAINEE.name())
		.anyRequest().authenticated().and().httpBasic(); // mechanism for authentication is basic 
		
		*
		*/
    	
    	// to make it work with authorities 
    	http.csrf().disable() //TODO :Never do this
	    .authorizeRequests() // Any request must be authenticates
		.antMatchers("/", "/index").permitAll() // except these urls
		.antMatchers("/api/**").hasRole(ApplicationUserRole.STUDENT.name())
		.antMatchers(HttpMethod.PUT ,"/management/api/v1/**").hasAnyAuthority(ApplicationUserPermissions.COURSE_WRITE.getPermission())
		.antMatchers(HttpMethod.POST ,"/management/api/v1/**").hasAnyAuthority(ApplicationUserPermissions.COURSE_WRITE.getPermission())
		.antMatchers(HttpMethod.DELETE,"/management/api/v1/**").hasAnyAuthority(ApplicationUserPermissions.COURSE_WRITE.getPermission())
		.antMatchers(HttpMethod.GET ,"/management/api/v1/**").hasAnyRole(ApplicationUserRole.ADMIN.name(), ApplicationUserRole.ADMINTRAINEE.name())
		.anyRequest().authenticated().and().httpBasic(); // mechanism for authentication is basic

    	

    }
    
    
    
    /**
     *         Using User Default Service
     * 1.  We need to use with Roles ; without Rolesconfig it will not work 
     * 2.  We need to pass the password using PasswordEncoder 
     * 
     *  
     * 
     * 
     * 
    **/
 
   // Following method shows how to use with role : to use you need to  
   // rename userDetailsService_using_Role to userDetailsService
   // and uncomment the following 2 annotations 
   // @Bean
   // @Override
    protected UserDetailsService userDetailsService_using_Role() {

	// Following code does not use password encoder this will thorw exception ; you
	// always need to use password encoder
	// UserDetails annasmithUser =
	// User.builder().username("annasmith").password("password").roles("STUDENT").build();

	UserDetails annasmithUser = User.builder().username("annasmith")
		.password(passwordEncoder.encode("password"))
		.roles(ApplicationUserRole.STUDENT.name()).build();

	UserDetails lindaUser = User.builder().username("linda")
		.password(passwordEncoder.encode("password123"))
		.roles(ApplicationUserRole.ADMIN.name()).build();
	
	
	UserDetails tomUser = User.builder().username("tom")
			.password(passwordEncoder.encode("password123"))
			.roles(ApplicationUserRole.ADMINTRAINEE.name()).build();

	return new InMemoryUserDetailsManager(annasmithUser, lindaUser, tomUser);
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
