package com.hrms.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.dto.JwtRequest;
import com.hrms.dto.JwtResponse;
import com.hrms.service.impl.CurrentUserDetails;
import com.hrms.service.impl.UserDetailsServiceImpl;
import com.hrms.utils.JwtUtils;

@RestController
@CrossOrigin("*")
public class AuthenticateController {
	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private JwtUtils jwtUtil;
	
	@Autowired
	private CurrentUserDetails userDetails;

	@PostMapping("/generate-token")
	public ResponseEntity<?> genarateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		System.out.println(jwtRequest);
		try {
			this.manager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Bad Credentials");

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Bad Credentials");
		}

		UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwtUtil.generateToken(userDetails);
		System.out.println("JWT: " + token);

		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	@GetMapping("/current-employee")
	public ResponseEntity<?> getCurrentUser(Principal principal) {
		UserDetails currentUser = this.userDetails.getCurrentUser(principal);
		return  ResponseEntity.ok(currentUser);
	}
}
