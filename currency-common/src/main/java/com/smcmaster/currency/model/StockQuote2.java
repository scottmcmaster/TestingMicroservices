package com.smcmaster.currency.model;

public class StockQuote2 {

	private String symbol;
	private double price;

	public StockQuote2() {
		
	}
	
	public StockQuote2(String symbol, double price) {
		this.symbol = symbol;
		this.price = price;
	}

	public String getSym() {
		return symbol;
	}

	public void setSym(String symbol) {
		this.symbol = symbol;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
