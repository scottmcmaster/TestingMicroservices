package com.smcmaster.localizedstockquoteapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Config {
	@Value("${currencyapi.host}")
	private String currencyApiHost;
	  
	@Value("${currencyapi.port}")
	private int currencyApiPort;
	  
	@Value("${stockquoteapi.host}")
	private String stockquoteApiHost;
	  
	@Value("${stockquoteapi.port}")
	private int stockquoteApiPort;

	public String getCurrencyApiHost() {
		String host = System.getenv("CURRENCYAPI_HOST");
		if (host != null) {
			return host;
		}
		return currencyApiHost;
	}

	public int getCurrencyApiPort() {
		String port = System.getenv("CURRENCYAPI_PORT");
		if (port != null) {
			return Integer.parseInt(port);
		}
		return currencyApiPort;
	}

	public String getStockquoteApiHost() {
		String host = System.getenv("STOCKQUOTEAPI_HOST");
		if (host != null) {
			return host;
		}
		return stockquoteApiHost;
	}

	public int getStockquoteApiPort() {
		String port = System.getenv("STOCKQUOTEAPI_PORT");
		if (port != null) {
			return Integer.parseInt(port);
		}
		return stockquoteApiPort;
	}
}
