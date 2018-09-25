package com.smcmaster.currencyapi.service;

import static io.specto.hoverfly.junit.core.HoverflyConfig.localConfigs;
import static io.specto.hoverfly.junit.core.SimulationSource.dsl;
import static io.specto.hoverfly.junit.dsl.HoverflyDsl.service;
import static io.specto.hoverfly.junit.dsl.ResponseCreators.serverError;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpServerErrorException;

import com.smcmaster.currencyapi.Application;

import io.specto.hoverfly.junit.rule.HoverflyRule;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class, ApplicationTestConfig.class})
public class TestCurrencyQuoteService_Error {
	
	@Autowired
	CurrencyQuoteService svc;

    @ClassRule
    public static HoverflyRule hoverflyRule = HoverflyRule.inSimulationMode(
    	dsl(service("free.currencyconverterapi.com")
            .get("/api/v6/convert")
            .queryParam("q", "USD_CNY")
            .willReturn(serverError())),
    	localConfigs().proxyPort(8500));
	
	@Test
	public void testGetBasicCurrencyQuote() {
		try {
			svc.retrieveQuote("USD", "CNY");
		} catch (RuntimeException ex) {
			assertEquals("Error occurred getting currency quote", ex.getMessage());
			assertTrue(ex.getCause() instanceof HttpServerErrorException);
			assertEquals(500, ((HttpServerErrorException)ex.getCause()).getRawStatusCode());
			return;
		}
		fail("Didn't get expected exception");
	}

}
