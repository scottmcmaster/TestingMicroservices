package com.smcmaster.currencyapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.smcmaster.currency.model.CurrencyQuote;
import com.smcmaster.currencyapi.free.Result;
import com.smcmaster.currencyapi.free.Results;

@Component
public class CurrencyQuoteService {

	@Autowired
	private RestTemplate restTemplate;
	
    @Bean
    public RestTemplate getRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
        		.setConnectTimeout(3000)
        		.build();
    }

	public CurrencyQuote retrieveQuote(String from, String to) {
		String id = from + "_" + to;
		String url = "https://free.currencyconverterapi.com/api/v6/convert?q=" + id;
		Results res;
		try {
			res = restTemplate.getForObject(url, Results.class);
		} catch (HttpServerErrorException ex) {
			throw new RuntimeException("Server error occurred getting currency quote", ex);
		} catch (ResourceAccessException rae) {
			throw new RuntimeException("Resource error occurred getting currency quote", rae);
		}
		Result r = res.getResults().get(id);
		return new CurrencyQuote(r.getFrom(), r.getTo(), r.getVal());
	}

}
