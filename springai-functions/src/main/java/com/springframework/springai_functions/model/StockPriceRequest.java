package com.springframework.springai_functions.model;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonClassDescription("stock price request")
public record StockPriceRequest(@JsonPropertyDescription("ticker name of the stock to quote")String ticker) {

}
