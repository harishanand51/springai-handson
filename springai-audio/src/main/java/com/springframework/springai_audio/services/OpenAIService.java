package com.springframework.springai_audio.services;


import com.springframework.springai_audio.model.Question;

public interface OpenAIService {
	
	byte[] getSpeech(Question question);

}
