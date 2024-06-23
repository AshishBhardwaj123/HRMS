package com.hrms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.entity.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

}
