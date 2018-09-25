package com.smcmaster.currencyapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.smcmaster.currency.model.CurrencyQuote;
import com.smcmaster.currencyapi.free.Result;
import com.smcmaster.currencyapi.free.Results;

@Component
public class CurrencyQuoteService {

	@Autowired
	private RestTemplate restTemplate;
	
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

	public CurrencyQuote retrieveQuote(String from, String to) {
		String id = from + "_" + to;
		String url = "https://free.currencyconverterapi.com/api/v6/convert?q=" + id;
		Results res;
		try {
			res = restTemplate.getForObject(url, Results.class);
		} catch (HttpServerErrorException ex) {
			throw new RuntimeException("Error occurred getting currency quote", ex);
		}
		Result r = res.getResults().get(id);
		return new CurrencyQuote(r.getFrom(), r.getTo(), r.getVal());
	}

}
