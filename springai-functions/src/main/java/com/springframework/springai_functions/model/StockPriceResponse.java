package com.springframework.springai_functions.model;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.math.BigDecimal;

@JsonClassDescription("stock price response")
public record StockPriceResponse(@JsonPropertyDescription("ticker symbol of the stock")String ticker,
		                         @JsonPropertyDescription("company name")String name,
		                         @JsonPropertyDescription("price of stock in USD")BigDecimal price,
		                         @JsonPropertyDescription("exchange the stock is traded on")String exchange,
		                         @JsonPropertyDescription("epoch time of the quote")Long updated
		) {

}
