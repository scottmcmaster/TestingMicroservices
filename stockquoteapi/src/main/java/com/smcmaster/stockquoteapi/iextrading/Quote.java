package com.smcmaster.stockquoteapi.iextrading;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {
	private String symbol;
	private double close;
	
	public double getClose() {
		return close;
	}
	public void setClose(double close) {
		this.close = close;
	}
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
}
