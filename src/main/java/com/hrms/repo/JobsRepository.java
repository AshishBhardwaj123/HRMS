package com.hrms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hrms.entity.Jobs;

@Repository
public interface JobsRepository extends JpaRepository<Jobs, Long> {
	
	@Query("SELECT p FROM Jobs p JOIN p.branch b WHERE b.branchName = :branchName")
    List<Jobs> findJobsByBranchName(@Param("branchName") String branchName);

}
