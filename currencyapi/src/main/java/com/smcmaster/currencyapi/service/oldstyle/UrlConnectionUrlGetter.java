package com.smcmaster.currencyapi.service.oldstyle;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

@Component
public class UrlConnectionUrlGetter implements UrlGetter {

	@Override
	public String getUrl(String address) throws Exception{
		URL url = new URL(address);
		URLConnection con = url.openConnection();
		InputStream in = con.getInputStream();
		String encoding = con.getContentEncoding();
		encoding = encoding == null ? "UTF-8" : encoding;
		return IOUtils.toString(in, encoding);
	}

}
