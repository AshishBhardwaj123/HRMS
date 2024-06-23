package com.hrms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.hrms.utils.AppConstant;

@Configuration
public class Bucket {

	private String awsAccessKey = AppConstant.AWSACCESSKEY;

	private String awsSecretKey =  AppConstant.AWSSECRETKEY;

	private String awsRegion = AppConstant.AWSREGION;

	@Bean
    String bucketName() {
        return AppConstant.BUCKETNAME;
    }


	@Bean
	AmazonS3 amazonS3() {
		BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsAccessKey, awsSecretKey);
		return AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
				.withRegion(awsRegion) // Set the AWS region explicitly
				.build();

	}
}
