package com.springSec.springSec;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyCustomUserDetailService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
	return new MyUserdetails();
    }

}