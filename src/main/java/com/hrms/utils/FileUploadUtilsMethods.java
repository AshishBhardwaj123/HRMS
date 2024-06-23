package com.hrms.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Component
public class FileUploadUtilsMethods {

	private static AmazonS3 s3Client;
	private static String bucketName;

	public FileUploadUtilsMethods(AmazonS3 s3Client, String bucketName) {
		FileUploadUtilsMethods.s3Client = s3Client;
		FileUploadUtilsMethods.bucketName = bucketName;
	}

	public static String generateKey(String originalFileName) {
		return originalFileName;
	}

	public static String uploadFileToS3(File file, String fileName) throws IOException {
		try {
			if (!file.exists() || !file.isFile()) {
				throw new FileNotFoundException(
						"Specified file does not exist or is not a file: " + file.getAbsolutePath());
			}
			// Upload the file to S3 bucket
			PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, file);
			s3Client.putObject(putObjectRequest);

			// Return the S3 URL of the uploaded file
			return "https://" + bucketName + ".s3.amazonaws.com/" + fileName;
		} catch (AmazonServiceException e) {
			e.printStackTrace();
			throw new IOException("Failed to upload file to Amazon S3: " + e.getMessage());
		}
	}

	public static File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
		try {
			String originalFilename = multipartFile.getOriginalFilename();
			if (originalFilename == null || originalFilename.isEmpty()) {
				throw new IllegalArgumentException("Invalid file: Original filename is null or empty.");
			}
			File file = File.createTempFile("uploaded-file-", null);
			multipartFile.transferTo(file);
			return file;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static void deleteFileFromS3(String fileName) throws IOException {
		try {
			System.out.println("7");
			s3Client.deleteObject(new DeleteObjectRequest(bucketName, fileName));
			System.out.println("Deleted file from S3: " + fileName);
		} catch (AmazonServiceException e) {
			e.printStackTrace();
			throw new IOException("Failed to delete file from Amazon S3: " + e.getMessage());
		}
	}

	public static String getFileNameFromUrl(String url) {
		int lastSlashIndex = url.lastIndexOf('/');
		System.out.println(url);
		String fileName = url.substring(lastSlashIndex + 1);
		// Replace %20 with +
		fileName = fileName.replace("%20", "+");
		System.out.println(fileName);
		// URL decode the filename
		return URLDecoder.decode(fileName, StandardCharsets.UTF_8);
	}
}
