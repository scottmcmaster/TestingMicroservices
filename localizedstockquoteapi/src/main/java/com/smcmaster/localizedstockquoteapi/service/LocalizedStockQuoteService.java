package com.smcmaster.localizedstockquoteapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.smcmaster.currency.model.CurrencyQuote;
import com.smcmaster.currency.model.StockQuote;
import com.smcmaster.localizedstockquoteapi.model.LocalizedStockQuote;

@Component
public class LocalizedStockQuoteService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	public LocalizedStockQuote retrieveQuote(String symbol, String currency) {
		CurrencyQuote curr = getCurrencyQuote(currency);
		StockQuote stock = getStockQuote(symbol);
		
		return new LocalizedStockQuote(stock.getSymbol(), curr.getVal() * stock.getPrice(), curr.getTo());
	}

	private CurrencyQuote getCurrencyQuote(String currency) {
		String url = "http://localhost:8080/currencyconverter/USD/" + currency;
		return restTemplate.getForObject(url, CurrencyQuote.class);
	}
	
	private StockQuote getStockQuote(String symbol) {
		String url = "http://localhost:8090/stockquote/" + symbol;
		return restTemplate.getForObject(url, StockQuote.class);
	}
}
