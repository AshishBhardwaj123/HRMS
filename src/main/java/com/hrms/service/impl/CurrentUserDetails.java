package com.hrms.service.impl;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserDetails {
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	public UserDetails getCurrentUser(Principal principal) {
		UserDetails userByUsername = this.userDetailsService.loadUserByUsername(principal.getName());
		return userByUsername;
	}

}
