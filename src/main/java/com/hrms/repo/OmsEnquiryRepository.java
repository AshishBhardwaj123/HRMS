package com.hrms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.entity.OmsEnquiry;

@Repository
public interface OmsEnquiryRepository extends JpaRepository<OmsEnquiry, Long> {

}
