package com.hrms.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.hrms.dto.OmsImagesDTO;

public interface OmsImagesService {
	OmsImagesDTO addImage(MultipartFile image, OmsImagesDTO dto) throws IOException;

	OmsImagesDTO updateImage(MultipartFile image, OmsImagesDTO dto, Long id) throws IOException;

	List<OmsImagesDTO> getAllImages();

	OmsImagesDTO getSingleImage(Long id);

	OmsImagesDTO getImageFromImgId(Long imgId);

	void deleteImage(Long id) throws IOException;
}
