package com.springframework.springai_functions.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springframework.springai_functions.model.Answer;
import com.springframework.springai_functions.model.Question;
import com.springframework.springai_functions.services.OpenAIService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class QuestionController {
	
	private final OpenAIService openAIService;
	
	 @Autowired
	    public QuestionController(OpenAIService openAIService) {
	        this.openAIService = openAIService;
	    }
	
	@PostMapping("/weather")
	public Answer askQuestion(@RequestBody Question question) {
		
		return openAIService.getAnswer(question);
	}
	
	@PostMapping("/stockprice")
	public Answer askStockPrice(@RequestBody Question question) {
		
		return openAIService.getStockPrice(question);
	}
	
	

}
