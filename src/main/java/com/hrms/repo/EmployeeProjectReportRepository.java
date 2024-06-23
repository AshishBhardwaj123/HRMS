package com.hrms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.entity.EmployeeProjectReport;

@Repository
public interface EmployeeProjectReportRepository extends JpaRepository<EmployeeProjectReport, Long> {

}
