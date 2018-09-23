package com.smcmaster.stockquoteapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.smcmaster.currency.model.StockQuote;
import com.smcmaster.stockquoteapi.service.StockQuoteService;

@RestController
public class StockQuoteController {

	@Autowired
	private StockQuoteService currencyQuoteService;
	
	@GetMapping("/stockquote/{symbol}")
	public StockQuote retrieveQuote(@PathVariable String symbol) {
		return currencyQuoteService.retrieveQuote(symbol);
	}
}
