package com.springSec.springSec;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MyUserdetails implements UserDetails {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getPassword() {
	// TODO Auto-generated method stub
	return passwordEncoder.encode("abc123");
    }

    @Override
    public String getUsername() {
	// TODO Auto-generated method stub
	return "user2";
    }

    @Override
    public boolean isAccountNonExpired() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean isAccountNonLocked() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean isEnabled() {
	// TODO Auto-generated method stub
	return false;
    }

}
