package com.hrms.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hrms.entity.Branch;
import com.hrms.entity.Department;
import com.hrms.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Optional<Employee> findByEmployeeId(String employeeId);
	
	@Query("SELECT e FROM Employee e WHERE e.branch.branchName = :branchName")
    List<Employee> findEmployeesByBranchName(@Param("branchName") String branchName);
	
	@Query("SELECT e FROM Employee e WHERE e.branch.branchName = :branchName ORDER BY e.id DESC")
    List<Employee> findEmployeesByBranchNameOrderByDescending(@Param("branchName") String branchName);
	
	List<Employee> findByDepartmentAndBranch(Department department, Branch branch);

}
