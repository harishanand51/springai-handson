package com.springframework.springai_audio.controllers;




import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springframework.springai_audio.model.Question;
import com.springframework.springai_audio.services.OpenAIService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class QuestionController {
	
	private final OpenAIService openAIService;

	public QuestionController(OpenAIService openAIService) {
		super();
		this.openAIService = openAIService;
	}
	
	
	@PostMapping(value="/talk", produces="audio/mpeg")
	public byte[] talkTalk(@RequestBody Question question) {
		
		return openAIService.getSpeech(question);
	}
	
	

}
