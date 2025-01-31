package com.springframework.springai_rag.controllers;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springframework.springai_rag.model.Answer;
import com.springframework.springai_rag.model.Question;
import com.springframework.springai_rag.services.OpenAIService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class QuestionController {
	
	private final OpenAIService openAIService=null;
	
	@PostMapping("/ask")
	public Answer askQuestion(@RequestBody Question question) {
		
		return openAIService.getAnswer(question);
	}
	
	

}
