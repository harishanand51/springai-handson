package com.springframework.springai_functions.functions;

import java.util.function.Function;

import org.springframework.web.client.RestClient;

import com.springframework.springai_functions.model.StockPriceRequest;
import com.springframework.springai_functions.model.StockPriceResponse;



public class StockQuoteFunction implements Function<StockPriceRequest, StockPriceResponse>{
	
	public static final String STOCK_URL = "https://api.api-ninjas.com/v1/stockprice";

    private final String apiNinjasKey;

	public StockQuoteFunction(String apiNinjasKey) {
		super();
		this.apiNinjasKey = apiNinjasKey;
	}

	@Override
	public StockPriceResponse apply(StockPriceRequest stockPriceRequest) {
		
		RestClient restClient = RestClient.builder()
                .baseUrl(STOCK_URL)
                .defaultHeaders(httpHeaders -> {
                	httpHeaders.set("X-Api-Key",apiNinjasKey);
                	httpHeaders.set("Accept", "application/json");
                    httpHeaders.set("Content-Type", "application/json");
                }).build();
		
		return restClient.get().uri( uriBuilder -> uriBuilder.queryParam("ticker", stockPriceRequest.ticker()).build()).retrieve().body(StockPriceResponse.class);
		
		
	}

	

}
