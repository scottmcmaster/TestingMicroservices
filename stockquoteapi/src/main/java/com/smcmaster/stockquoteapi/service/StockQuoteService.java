package com.smcmaster.stockquoteapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.smcmaster.currency.model.StockQuote;
import com.smcmaster.currency.model.StockQuote2;
import com.smcmaster.stockquoteapi.iextrading.Quote;

@Component
public class StockQuoteService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	public StockQuote retrieveQuote(String symbol) {
		String url = "https://api.iextrading.com/1.0/stock/" + symbol + "/quote";
		Quote q = restTemplate.getForObject(url, Quote.class);
		return new StockQuote(q.getSymbol(), q.getClose());
		
		// Change to StockQuote2 to see a Pact test break.
		//return new StockQuote2(q.getSymbol(), q.getClose());
	}

}
