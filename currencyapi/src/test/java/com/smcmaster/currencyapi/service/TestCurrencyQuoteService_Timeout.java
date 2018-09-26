package com.smcmaster.currencyapi.service;

import static io.specto.hoverfly.junit.core.HoverflyConfig.localConfigs;
import static io.specto.hoverfly.junit.core.SimulationSource.dsl;
import static io.specto.hoverfly.junit.dsl.HoverflyDsl.service;
import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.ResourceAccessException;

import com.smcmaster.currencyapi.Application;

import io.specto.hoverfly.junit.rule.HoverflyRule;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { Application.class, ApplicationTestConfig.class })
public class TestCurrencyQuoteService_Timeout {

	@Autowired
	CurrencyQuoteService svc;

	@ClassRule
	public static HoverflyRule hoverflyRule = HoverflyRule.inSimulationMode(
			dsl(service("free.currencyconverterapi.com").get("/api/v6/convert").queryParam("q", "USD_CNY")
					
					.willReturn(success())
					.andDelay(5, TimeUnit.SECONDS)
					.forMethod("GET")),
			localConfigs().proxyPort(8500));

	@AfterClass
	public static void tearDown() {
		// Haven't found a good way to force-quit Hoverfly or else wait for it to finish
		// what it thinks it's doing in the delay scenario.
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
	}
	
	@Test
	@Ignore
	public void testGetCurrencyQuoteTimeout() {
		try {
			svc.retrieveQuote("USD", "CNY");
		} catch (RuntimeException ex) {
			assertEquals("Resource error occurred getting currency quote", ex.getMessage());
			assertTrue(ex.getCause() instanceof ResourceAccessException);
			assertTrue(ex.getCause().getCause() instanceof SocketTimeoutException);
			return;
		}
		fail("Didn't get expected exception");
	}

}
