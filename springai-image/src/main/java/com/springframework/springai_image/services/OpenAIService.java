package com.springframework.springai_image.services;

import org.springframework.web.multipart.MultipartFile;

import com.springframework.springai_image.model.Question;

public interface OpenAIService {
	
	byte[] getImage(Question question);
	
	String getDescription(MultipartFile file);

}
