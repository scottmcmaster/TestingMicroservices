package com.smcmaster.localizedstockquoteapi.model;

public class LocalizedStockQuote {

	private String symbol;
	private String currency;
	private double price;

	public LocalizedStockQuote() {
		
	}
	
	public LocalizedStockQuote(String symbol, double price, String currency) {
		this.symbol = symbol;
		this.price = price;
		this.currency = currency;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
