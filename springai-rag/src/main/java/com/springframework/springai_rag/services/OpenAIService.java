package com.springframework.springai_rag.services;

import com.springframework.springai_rag.model.Answer;
import com.springframework.springai_rag.model.Question;

public interface OpenAIService {
	
	Answer getAnswer(Question question);

}
