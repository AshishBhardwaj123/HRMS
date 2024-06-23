package com.hrms.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hrms.dto.OmsImagesDTO;
import com.hrms.entity.OmsImages;
import com.hrms.exception.ResourceNotFoundException;
import com.hrms.repo.OmsImagesRepo;
import com.hrms.service.OmsImagesService;
import com.hrms.utils.FileUploadUtilsMethods;

@Service
public class OmsImagesServiceImpl implements OmsImagesService {

	@Autowired
	private OmsImagesRepo imgRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public OmsImagesDTO addImage(MultipartFile image, OmsImagesDTO dto) throws IOException {
		if (!image.isEmpty()) {
			Random random = new Random();
			Long randomNumber = 10000 + random.nextLong(90000);
			String key = FileUploadUtilsMethods.generateKey(image.getOriginalFilename());
			String profilePath = FileUploadUtilsMethods
					.uploadFileToS3(FileUploadUtilsMethods.convertMultipartFileToFile(image), key);
			dto.setImgPath(profilePath);
			dto.setImgId(randomNumber);
			dto.setS3Key(key);
		}

		OmsImages mapped = this.mapper.map(dto, OmsImages.class);
		OmsImages saved = this.imgRepo.save(mapped);
		OmsImagesDTO imagesDTO = this.mapper.map(saved, OmsImagesDTO.class);
		return imagesDTO;
	}

	@Override
	public OmsImagesDTO updateImage(MultipartFile image, OmsImagesDTO dto, Long id) throws IOException {
		OmsImages images = this.imgRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Image", "id", id));
		images.setId(id);
		if (!image.isEmpty()) {
			String key = FileUploadUtilsMethods.generateKey(image.getOriginalFilename());
			String profilePath = FileUploadUtilsMethods
					.uploadFileToS3(FileUploadUtilsMethods.convertMultipartFileToFile(image), key);
			images.setImgPath(profilePath);
			images.setImgId(images.getImgId());
		}

		OmsImages saved = this.imgRepo.save(images);
		OmsImagesDTO imagesDTO = this.mapper.map(saved, OmsImagesDTO.class);
		return imagesDTO;
	}

	@Override
	public List<OmsImagesDTO> getAllImages() {
		List<OmsImages> findAll = this.imgRepo.findAll();
		List<OmsImagesDTO> collectedList = findAll.stream().map(img -> {
			OmsImagesDTO mapped = this.mapper.map(img, OmsImagesDTO.class);
			return mapped;
		}).collect(Collectors.toList());
		return collectedList;
	}

	@Override
	public OmsImagesDTO getSingleImage(Long id) {
		OmsImages image = this.imgRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Image", "id", id));
		OmsImagesDTO omsImagesDTO = this.mapper.map(image, OmsImagesDTO.class);
		return omsImagesDTO;
	}

	@Override
	public OmsImagesDTO getImageFromImgId(Long imgId) {
		OmsImages image = this.imgRepo.findByImgId(imgId)
				.orElseThrow(() -> new ResourceNotFoundException("Image", "id", imgId));
		OmsImagesDTO omsImagesDTO = this.mapper.map(image, OmsImagesDTO.class);
		return omsImagesDTO;
	}

	@Override
	public void deleteImage(Long id) throws IOException {
		System.out.println("3");
		OmsImages image = this.imgRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Image", "id", id));
		System.out.println("4");
		String key = FileUploadUtilsMethods.getFileNameFromUrl(image.getImgPath());
		System.out.println("5");
		FileUploadUtilsMethods.deleteFileFromS3(key);
		System.out.println("6");
		this.imgRepo.deleteById(id);
	}

}
