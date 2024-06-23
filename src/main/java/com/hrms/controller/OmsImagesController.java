package com.hrms.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrms.dto.OmsImagesDTO;
import com.hrms.service.OmsImagesService;

@CrossOrigin("*")
@RestController
@RequestMapping("/oms/image")
public class OmsImagesController {

	@Autowired
	private OmsImagesService imgService;

//	post new image
	@PostMapping("/add")
	public ResponseEntity<OmsImagesDTO> addImage(@RequestParam("img") MultipartFile img,
			@RequestParam("dto") String json) throws IOException {
		ObjectMapper map = new ObjectMapper();
		OmsImagesDTO imagesDTO = map.readValue(json, OmsImagesDTO.class);
		OmsImagesDTO addImage = this.imgService.addImage(img, imagesDTO);
		return new ResponseEntity<OmsImagesDTO>(addImage, HttpStatus.CREATED);
	}
	
//	update image
	@PutMapping("/updateImage/{id}")
	public ResponseEntity<OmsImagesDTO> addImage(@RequestParam("img") MultipartFile img,
			@RequestParam("dto") String json, @PathVariable Long id) throws IOException {
		ObjectMapper map = new ObjectMapper();
		OmsImagesDTO imagesDTO = map.readValue(json, OmsImagesDTO.class);
		OmsImagesDTO updateImage = this.imgService.updateImage(img, imagesDTO, id);
		return new ResponseEntity<OmsImagesDTO>(updateImage, HttpStatus.CREATED);
	}
	
//	get all image
	@GetMapping("/getAllImage")
	public ResponseEntity<List<OmsImagesDTO>> getAllImage() {
		List<OmsImagesDTO> allImages = this.imgService.getAllImages();
		return new ResponseEntity<List<OmsImagesDTO>>(allImages, HttpStatus.CREATED);
	}
	
//	get single image
	@GetMapping("/getImage/{id}")
	public ResponseEntity<OmsImagesDTO> getImage(@PathVariable Long id) {
		OmsImagesDTO images = this.imgService.getSingleImage(id);
		return new ResponseEntity<OmsImagesDTO>(images, HttpStatus.OK);
	}
	
//	get image with image id
	@GetMapping("/getImageWithImageId/{id}")
	public ResponseEntity<OmsImagesDTO> getImageWithImgId(@PathVariable Long id) {
		OmsImagesDTO images = this.imgService.getImageFromImgId(id);
		return new ResponseEntity<OmsImagesDTO>(images, HttpStatus.OK);
	}
	
//	delete image 
	@DeleteMapping("/deleteImage/{id}")
	public ResponseEntity<?> deleteImage(@PathVariable Long id) throws IOException {
		System.out.println("1");
		this.imgService.deleteImage(id);
		System.out.println("2");
		return ResponseEntity.ok("Image Deleted Successfully");
	}	
}
