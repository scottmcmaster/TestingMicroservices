package com.smcmaster.currencyapi.service;

import static io.specto.hoverfly.junit.core.HoverflyConfig.localConfigs;
import static io.specto.hoverfly.junit.core.SimulationSource.defaultPath;
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
public class TestCurrencyQuoteService_CaptureAndSimulate {
	
	@Autowired
	CurrencyQuoteService svc;

	//@ClassRule
	//public static HoverflyRule hoverflyRule = HoverflyRule.inCaptureMode("usd_cny.json",
	//		localConfigs().proxyPort(8500));
		
	@ClassRule
	public static HoverflyRule hoverflyRule = HoverflyRule.inSimulationMode(
			defaultPath("usd_cny.json"),
			localConfigs().proxyPort(8500));
		
	@Test
	public void testGetBasicCurrencyQuote() {
		CurrencyQuote quote = svc.retrieveQuote("USD", "CNY");
		assertEquals(6.866971, quote.getVal(), 0.001);
	}

}
