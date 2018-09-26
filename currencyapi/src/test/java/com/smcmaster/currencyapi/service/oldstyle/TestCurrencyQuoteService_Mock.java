package com.smcmaster.currencyapi.service.oldstyle;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.mockito.Mockito.*;

import com.smcmaster.currency.model.CurrencyQuote;
import com.smcmaster.currencyapi.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class})
public class TestCurrencyQuoteService_Mock {
	
	@Mock
	private UrlGetter mockGetter;
	
	@InjectMocks
	@Autowired
	CurrencyQuoteServiceOldStyle svc;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetBasicCurrencyQuote() throws Exception {
		when(mockGetter.getUrl("https://free.currencyconverterapi.com/api/v6/convert?q=USD_CNY"))
			.thenReturn("{\"query\":{\"count\":1},\"results\":{\"USD_CNY\":{\"id\":\"USD_CNY\",\"val\":6.878899,\"to\":\"CNY\",\"fr\":\"USD\"}}}");
		
		CurrencyQuote quote = svc.retrieveQuote("USD", "CNY");
		assertEquals(6.878899, quote.getVal(), 0.001);
	}

}
