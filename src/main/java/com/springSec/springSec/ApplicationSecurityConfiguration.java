package com.springSec.springSec;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springSec.configs.ApplicationUserRole;
import com.springSec.configs.auth.ApplicationUserService;

@Configuration
@EnableWebSecurity
// Enable following annotation if we want to put authorization checks on the method level 
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan("com.springSec.configs.auth")
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final PasswordEncoder passwordEncoder;
	private final ApplicationUserService applicationUserService;

	@Autowired
    public ApplicationSecurityConfiguration(PasswordEncoder passwordEncoder,
    		ApplicationUserService applicationUserService) {
		this.passwordEncoder = passwordEncoder;
		this.applicationUserService = applicationUserService;
	}


	@Override
    protected void configure(HttpSecurity http) throws Exception {

	
    	// to make it work with authorities 
    	http.csrf().disable() //TODO :Never do this
	    .authorizeRequests() // Any request must be authenticates
		.antMatchers("/", "/index").permitAll() // except these urls
		.antMatchers("/api/**").hasRole(ApplicationUserRole.STUDENT.name())
		.anyRequest().authenticated().and()
		.formLogin()  // mechanism for authentication is basic
			.loginPage("/login") // custom login page
    		.permitAll()
    		.defaultSuccessUrl("/courses",true)
    		// If we want to change teh default parameters for password, username and remember-me
    		// you need to change the parameters in Thymeleaf login page
    		//.passwordParameter("mypassword")
    		//.usernameParameter("")
    		
    		
    	.and()
    	.rememberMe() //Default 2 weeks
	    	.tokenValiditySeconds((int)TimeUnit.DAYS.toSeconds(21))
	    	.key("somethingverysecured") // key for the DB where the MD5 hash is stored for userid + timetamp
    	    //.rememberMeParameter(null)
	    	//Optional part 
    	.and()
    	.logout()
    	.logoutUrl("/logout")
    	.clearAuthentication(true)
    	.invalidateHttpSession(true)
    	//Coockies are copied from network response 
         .deleteCookies("JSESSIONID","remember-me")
         .logoutSuccessUrl("/login");
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 auth.authenticationProvider(daoAuthenticationProvider());
	}
    
    
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
    	
    	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    	provider.setPasswordEncoder(passwordEncoder);
    	provider.setUserDetailsService(applicationUserService);
    	
    	
    	return provider;
    	
    }
    
    
     
   /* 
    
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {

	//Use Builder to create simple static user 
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
    }*/

}
