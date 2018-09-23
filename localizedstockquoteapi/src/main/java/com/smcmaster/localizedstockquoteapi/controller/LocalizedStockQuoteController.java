package com.smcmaster.localizedstockquoteapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.smcmaster.localizedstockquoteapi.model.LocalizedStockQuote;
import com.smcmaster.localizedstockquoteapi.service.LocalizedStockQuoteService;

@RestController
public class LocalizedStockQuoteController {

	@Autowired
	private LocalizedStockQuoteService localizedQuoteService;
	
	@GetMapping("/localizedstockquote/{symbol}/{currency}")
	public LocalizedStockQuote retrieveQuote(@PathVariable String symbol, @PathVariable String currency) {
		return localizedQuoteService.retrieveQuote(symbol, currency);
	}
}
