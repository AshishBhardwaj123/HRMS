package com.hrms.component;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hrms.service.impl.UserDetailsServiceImpl;
import com.hrms.utils.JwtUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsServiceImpl userDetailService;

	@Autowired
	private JwtUtils jwt;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String requstTokenHeader = request.getHeader("Authorization");

		String username = null;
		String jwtToken = null;
		
		if (requstTokenHeader != null && requstTokenHeader.startsWith("Bearer ")) {
			try {
				jwtToken = requstTokenHeader.substring(7);
				try {
					username = this.jwt.extractUsername(jwtToken);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				final UserDetails userDetails = this.userDetailService.loadUserByUsername(username);

				if (this.jwt.validateToken(jwtToken, userDetails)) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationFilter = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					usernamePasswordAuthenticationFilter
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationFilter);
				}
			} else {
				System.out.println("Token is not valid!!");
			}
		} else {
			System.out.println("Invalid Token!!!");
		}

		filterChain.doFilter(request, response);

	}

}
