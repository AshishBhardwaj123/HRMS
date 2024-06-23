package com.hrms.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class EmployeeDetails implements UserDetails {

	private Employee employee;
	
	public Employee getEmployee() {
        return employee;
    }

	public EmployeeDetails(Employee employee) {
		this.employee = employee;
	}
	
	public Branch getBranch() {
		return employee.getBranch();
	}
	
	public Department getDepartment() {
		return employee.getDepartment();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(this.employee.getEmployeeRole().name());
		return List.of(simpleGrantedAuthority);
	}

	@Override
	public String getUsername() {
		return this.employee.getEmployeeId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getPassword() {
		return this.employee.getPassword();
	}

}
