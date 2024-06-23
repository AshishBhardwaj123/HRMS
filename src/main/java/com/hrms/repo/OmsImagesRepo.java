package com.hrms.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.entity.OmsImages;

@Repository
public interface OmsImagesRepo extends JpaRepository<OmsImages, Long> {
	Optional<OmsImages> findByImgId(Long id);
}
