package com.smcmaster.currencyapi.service.springstyle;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import com.smcmaster.currency.model.CurrencyQuote;
import com.smcmaster.currencyapi.service.CurrencyQuoteService;

@RunWith(SpringRunner.class)
@RestClientTest(CurrencyQuoteService.class)
public class TestCurrencyQuoteService_SpringStyle {

	@Autowired
    private MockRestServiceServer server;
 
    @Autowired
    private CurrencyQuoteService svc;
    
    @Before
    public void setUp() throws Exception {
        server.expect(requestTo("https://free.currencyconverterapi.com/api/v6/convert?q=USD_CNY"))
          .andRespond(withSuccess("{\"query\":{\"count\":1},\"results\":{\"USD_CNY\":{\"id\":\"USD_CNY\",\"val\":6.878899,\"to\":\"CNY\",\"fr\":\"USD\"}}}", MediaType.APPLICATION_JSON));
    }
    
	@Test
	public void testGetBasicCurrencyQuote() {
		CurrencyQuote quote = svc.retrieveQuote("USD", "CNY");
		assertEquals(6.878899, quote.getVal(), 0.001);
	}

}
