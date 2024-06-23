package com.hrms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hrms.entity.Leaves;

public interface EmployeeLeaveRepository extends JpaRepository<Leaves, Long> {
	List<Leaves> findAllByOrderByLeaveIdDesc();

	@Query("SELECT l FROM Leaves l JOIN l.employee e JOIN e.branch b WHERE b.branchName = :branchName ORDER BY l.leaveId DESC")
	List<Leaves> findLeavesByEmployeeBranchName(@Param("branchName") String branchName);

	@Query("SELECT l FROM Leaves l WHERE l.employee.employeeRole = com.hrms.enums.RoleStatus.ROLE_MANAGEMENT")
	List<Leaves> findLeavesByRoleManagement();
}
