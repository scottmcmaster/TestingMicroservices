package com.smcmaster.currencyapi.service.oldstyle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smcmaster.currency.model.CurrencyQuote;
import com.smcmaster.currencyapi.free.Result;
import com.smcmaster.currencyapi.free.Results;

@Component
public class CurrencyQuoteServiceOldStyle {

	@Autowired
	private UrlGetter urlGetter;
	
	@Autowired
	private ObjectMapper mapper;

	public CurrencyQuote retrieveQuote(String from, String to) {
		String id = from + "_" + to;
		String url = "https://free.currencyconverterapi.com/api/v6/convert?q=" + id;
		try {
			String json = urlGetter.getUrl(url);
			Results res = mapper.readValue(json, Results.class);
			Result r = res.getResults().get(id);
			return new CurrencyQuote(r.getFrom(), r.getTo(), r.getVal());
		} catch (Exception ex) {
			throw new RuntimeException("Error occurred getting currency quote", ex);
		}
	}

}
