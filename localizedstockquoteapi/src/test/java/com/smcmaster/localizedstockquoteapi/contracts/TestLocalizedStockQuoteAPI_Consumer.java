package com.smcmaster.localizedstockquoteapi.contracts;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import au.com.dius.pact.consumer.*;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.smcmaster.localizedstockquoteapi.model.LocalizedStockQuote;
import com.smcmaster.localizedstockquoteapi.service.LocalizedStockQuoteService;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
        "currencyapi.host: localhost",
        "currencyapi.port: 8080",
        "stockquoteapi.host: localhost",
        "stockquoteapi.port: 8081",
})
public class TestLocalizedStockQuoteAPI_Consumer {

	@Autowired
	private LocalizedStockQuoteService svc;
	
    @Rule
    public PactProviderRuleMk2 mockCurrencyProvider = new PactProviderRuleMk2("currencyapi", "localhost", 8080, this);

    @Rule
    public PactProviderRuleMk2 mockStockQuoteProvider = new PactProviderRuleMk2("stockquoteapi", "localhost", 8081, this);
    
    @Pact(provider="stockquoteapi", consumer="localizedstockquoteapi")
    public RequestResponsePact createStockQuoteApiPact(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");

        DslPart body = new PactDslJsonBody()
        		.stringValue("symbol", "BABA")
        		.decimalType("price");

        return builder
        	.given("localizedstockquoteapi to stockquoteapi")
            .uponReceiving("stockquoteapi test interaction")
                .path("/stockquote/BABA")
                .method("GET")
            .willRespondWith()
                .status(200)
                .headers(headers)
                .body(body)
            .toPact();
    }
    
    @Pact(provider="currencyapi", consumer="localizedstockquoteapi")
    public RequestResponsePact createCurrencyApiPact(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");

        DslPart body = new PactDslJsonBody()
        		.stringType("to", "CNY")
        		.stringType("from", "USD")
        		.decimalType("val");
        
        return builder
        	.given("localizedstockquoteapi to currencyapi")
            .uponReceiving("currencyapi test interaction")
                .path("/currencyconverter/USD/CNY")
                .method("GET")
            .willRespondWith()
                .status(200)
                .headers(headers)
                .body(body)
            .toPact();
    }
    
    @Test
    @PactVerification({"currencyapi", "stockquoteapi"})
    public void runTest() {
        LocalizedStockQuote quote = svc.retrieveQuote("BABA", "CNY");
        assertEquals("BABA", quote.getSymbol());
    }
}
