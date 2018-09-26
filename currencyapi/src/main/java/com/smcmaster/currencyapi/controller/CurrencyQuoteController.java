package com.smcmaster.currencyapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.smcmaster.currency.model.CurrencyQuote;
import com.smcmaster.currencyapi.service.CurrencyQuoteService;

@RestController
public class CurrencyQuoteController {

	@Autowired
	private CurrencyQuoteService currencyQuoteService;
	
	@GetMapping(path = "/currencyconverter/{from}/{to}", produces = "application/json")
	public CurrencyQuote retrieveQuote(@PathVariable String from, @PathVariable String to) {
		return currencyQuoteService.retrieveQuote(from, to);
	}
}
