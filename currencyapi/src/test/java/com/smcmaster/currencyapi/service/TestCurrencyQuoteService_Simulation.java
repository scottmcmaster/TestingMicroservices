package com.smcmaster.currencyapi.service;

import static io.specto.hoverfly.junit.core.HoverflyConfig.localConfigs;
import static io.specto.hoverfly.junit.core.SimulationSource.dsl;
import static io.specto.hoverfly.junit.dsl.HoverflyDsl.service;
import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;
import static org.junit.Assert.assertEquals;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.smcmaster.currency.model.CurrencyQuote;
import com.smcmaster.currencyapi.Application;

import io.specto.hoverfly.junit.rule.HoverflyRule;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class, ApplicationTestConfig.class})
public class TestCurrencyQuoteService_Simulation {
	
	@Autowired
	CurrencyQuoteService svc;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inSimulationMode(
    	dsl(service("free.currencyconverterapi.com")
            .get("/api/v6/convert")
            .queryParam("q", "USD_CNY")
            .willReturn(
            		success("{\"query\":{\"count\":1},\"results\":{\"USD_CNY\":{\"id\":\"USD_CNY\",\"val\":6.878899,\"to\":\"CNY\",\"fr\":\"USD\"}}}", "application/json"))),
    	localConfigs().proxyPort(8500));
	
	@Test
	public void testGetBasicCurrencyQuote() {
		CurrencyQuote quote = svc.retrieveQuote("USD", "CNY");
		assertEquals(6.878899, quote.getVal(), 0.001);
	}
}
