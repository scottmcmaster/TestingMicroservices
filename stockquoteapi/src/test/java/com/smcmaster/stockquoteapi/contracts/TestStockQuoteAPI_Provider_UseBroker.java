package com.smcmaster.stockquoteapi.contracts;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.web.context.ConfigurableWebApplicationContext;

import com.smcmaster.stockquoteapi.Application;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;

@RunWith(PactRunner.class)
@Provider("stockquoteapi")
@PactBroker(host = "localhost", port = "32769")
public class TestStockQuoteAPI_Provider_UseBroker {

	@TestTarget
	public final Target target = new HttpTarget("http", "localhost", 8090);
	
	private static ConfigurableWebApplicationContext application;
	
	@BeforeClass
	public static void start() {
		application = (ConfigurableWebApplicationContext) SpringApplication.run(Application.class);
	}

	@State("localizedstockquoteapi to stockquoteapi")
	public void toGetState() {
	}
}
