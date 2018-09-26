package com.smcmaster.currencyapi.controller.oldstyle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.smcmaster.currency.model.CurrencyQuote;
import com.smcmaster.currencyapi.service.oldstyle.CurrencyQuoteServiceOldStyle;

@RestController
public class CurrencyQuoteControllerOldStyle {

	@Autowired
	private CurrencyQuoteServiceOldStyle currencyQuoteService;
	
	@GetMapping("/currencyconverteroldstyle/{from}/{to}")
	public CurrencyQuote retrieveQuote(@PathVariable String from, @PathVariable String to) {
		return currencyQuoteService.retrieveQuote(from, to);
	}
}
