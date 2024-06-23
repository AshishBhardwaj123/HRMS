package com.hrms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hrms.entity.Employee;
import com.hrms.entity.EmployeeDetails;
import com.hrms.exception.ResourceNotFoundException;
import com.hrms.repo.EmployeeRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private EmployeeRepository empRepo;

	@Override
	public UserDetails loadUserByUsername(String employeeId) throws UsernameNotFoundException {
		Employee employee = this.empRepo.findByEmployeeId(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
		return new EmployeeDetails(employee);
	}

}
