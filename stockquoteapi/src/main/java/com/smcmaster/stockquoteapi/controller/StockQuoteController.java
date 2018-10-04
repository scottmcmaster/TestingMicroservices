package com.smcmaster.stockquoteapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.smcmaster.currency.model.StockQuote;
import com.smcmaster.currency.model.StockQuote2;
import com.smcmaster.stockquoteapi.service.StockQuoteService;

@RestController
public class StockQuoteController {

	@Autowired
	private StockQuoteService currencyQuoteService;
	
	@GetMapping(path = "/stockquote/{symbol}", produces = "application/json")
	public StockQuote retrieveQuote(@PathVariable String symbol) {
		return currencyQuoteService.retrieveQuote(symbol);
	}
}
