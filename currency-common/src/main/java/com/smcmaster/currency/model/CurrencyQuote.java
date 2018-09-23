package com.smcmaster.currency.model;

public class CurrencyQuote {

	private String to;
	private String from;
	private double val;

	public CurrencyQuote() {
		
	}
	
	public CurrencyQuote(String from, String to, double val) {
		this.from = from;
		this.to = to;
		this.val = val;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public double getVal() {
		return val;
	}

	public void setVal(double val) {
		this.val = val;
	}

}
