package com.smcmaster.currencyapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.smcmaster.currency.model.CurrencyQuote;
import com.smcmaster.currencyapi.free.Result;
import com.smcmaster.currencyapi.free.Results;

@Component
public class CurrencyQuoteService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	public CurrencyQuote retrieveQuote(String from, String to) {
		String id = from + "_" + to;
		String url = "https://free.currencyconverterapi.com/api/v6/convert?q=" + id;
		Results res = restTemplate.getForObject(url, Results.class);
		Result r = res.getResults().get(id);
		return new CurrencyQuote(r.getFrom(), r.getTo(), r.getVal());
	}

}
