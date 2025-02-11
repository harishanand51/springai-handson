package com.springframework.springai_functions.services;

import java.util.List;


import org.springframework.ai.chat.messages.Message;

import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.model.ModelOptionsUtils;
import org.springframework.ai.model.function.FunctionCallback;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import com.springframework.springai_functions.functions.StockQuoteFunction;
import com.springframework.springai_functions.functions.WeatherServiceFunction;
import com.springframework.springai_functions.model.Answer;
import com.springframework.springai_functions.model.Question;
import com.springframework.springai_functions.model.StockPriceRequest;
import com.springframework.springai_functions.model.StockPriceResponse;
import com.springframework.springai_functions.model.WeatherRequest;
import com.springframework.springai_functions.model.WeatherResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OpenAIServiceImpl implements OpenAIService {
	
	@Value("${sfg.aiapp.apiNinjasKey}")
	private String apiNinjasKey;
	
	private final OpenAiChatModel openAiChatModel;

	public OpenAIServiceImpl(OpenAiChatModel openAiChatModel) {
		this.openAiChatModel = openAiChatModel;
	}

	@Override
	public Answer getAnswer(Question question) {
		
		var promptOptions = OpenAiChatOptions.builder()
                               .functionCallbacks(List.of(FunctionCallback.builder()
                            		   .function("currentWeather", new WeatherServiceFunction(apiNinjasKey))
                            		   .description("Get the current weather for the location")
                            		   .inputType(WeatherRequest.class)
                            		   .responseConverter(response -> {
                            			   String schema = ModelOptionsUtils.getJsonSchema(WeatherResponse.class, false);
                            			   String json = ModelOptionsUtils.toJsonString(response);
                            			   return schema+"\n"+json;
                            		   })
                            		   .build()))	
                               .build();
		
		Message userMessage = new PromptTemplate(question.question()).createMessage();
		
		Message systemMessage = new SystemPromptTemplate("You are a weather service. You receive weather information from a service which gives you the information based on the metrics system." +
	                " When answering the weather in an imperial system country, you should convert the temperature to Fahrenheit and the wind speed to miles per hour. ").createMessage();
		
		var response = openAiChatModel.call(new Prompt(List.of(userMessage, systemMessage), promptOptions));
		
		
		return new Answer(response.getResult().getOutput().getContent());
	}
	
	
	@Override
	public Answer getStockPrice(Question question) {
		
		var promptOptions = OpenAiChatOptions.builder()
                               .functionCallbacks(List.of(FunctionCallback.builder()
                            		   .function("stockPrice", new StockQuoteFunction(apiNinjasKey))
                            		   .description("Get the current stock price for the stock symbol")
                            		   .inputType(StockPriceRequest.class)
                            		   .responseConverter(response -> {
                            			   String schema = ModelOptionsUtils.getJsonSchema(StockPriceResponse.class, false);
                            			   String json = ModelOptionsUtils.toJsonString(response);
                            			   return schema+"\n"+json;
                            		   })
                            		   .build()))	
                               .build();
		
		Message userMessage = new PromptTemplate(question.question()).createMessage();
		
		Message systemMessage = new SystemPromptTemplate("You are an agent which provides the current stock price for the given symbol(or ticker).").createMessage();
		
		var response = openAiChatModel.call(new Prompt(List.of(userMessage, systemMessage), promptOptions));
		
		
		return new Answer(response.getResult().getOutput().getContent());
	}

	

}
