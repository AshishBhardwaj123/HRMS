package com.hrms.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hrms.entity.Projects;

@Repository
public interface ProjectsRepository extends JpaRepository<Projects, Long> {
	
	Optional<Projects> findByProjectId(String projectId);

	List<Projects> findAllByOrderByIdDesc();

	@Query("SELECT p FROM Projects p JOIN p.branch b WHERE b.branchName = :branchName")
    List<Projects> findProjectsByBranchName(@Param("branchName") String branchName);
}
