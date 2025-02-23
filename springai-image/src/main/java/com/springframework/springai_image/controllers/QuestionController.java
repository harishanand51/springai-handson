package com.springframework.springai_image.controllers;



import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springframework.springai_image.model.Question;
import com.springframework.springai_image.services.OpenAIService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class QuestionController {
	
	private final OpenAIService openAIService;

	public QuestionController(OpenAIService openAIService) {
		super();
		this.openAIService = openAIService;
	}
	
	@PostMapping(value="/image", produces=MediaType.IMAGE_PNG_VALUE)
	public byte[] getImage(@RequestBody Question question) {
		
		return openAIService.getImage(question);
	}
	
	
	@PostMapping(value = "/vision", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> upload(
		@Validated @RequestParam("file") MultipartFile file,
		@RequestParam("name") String name
		)throws IOException{
		
		return ResponseEntity.ok(openAIService.getDescription(file));
	}
	
	

}
