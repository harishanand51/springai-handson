package com.springframework.springai_functions.services;

import com.springframework.springai_functions.model.Answer;
import com.springframework.springai_functions.model.Question;

public interface OpenAIService {
	
	Answer getAnswer(Question question);
	
	Answer getStockPrice(Question question);

}
