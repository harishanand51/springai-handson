package com.springframework.springaiintro.services;


import com.springframework.springaiintro.models.Answer;
import com.springframework.springaiintro.models.GetCapitalRequest;
import com.springframework.springaiintro.models.GetCapitalResponse;
import com.springframework.springaiintro.models.GetCapitalWithInfoResponse;
import com.springframework.springaiintro.models.Question;


public interface OpenAIService {

    //Answer getCapitalWithInfo(GetCapitalRequest getCapitalRequest);
	GetCapitalWithInfoResponse getCapitalWithInfo(GetCapitalRequest getCapitalRequest);
    
    GetCapitalResponse getCapital(GetCapitalRequest getCapitalRequest);
    
    String getAnswer(String question);

    Answer getAnswer(Question question);
}
