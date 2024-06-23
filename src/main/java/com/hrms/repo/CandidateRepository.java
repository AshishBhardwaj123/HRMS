package com.hrms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hrms.entity.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
	List<Candidate> findAllByOrderByCandidateIdDesc();
	
	@Query("SELECT c FROM Candidate c JOIN c.appliedFor j JOIN j.branch b WHERE b.branchName = :branchName ORDER BY c.candidateId DESC")
    List<Candidate> findCandidatesByBranchName(@Param("branchName") String branchName);
}
