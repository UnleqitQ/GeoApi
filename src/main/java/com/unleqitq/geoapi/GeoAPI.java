package com.unleqitq.geoapi;

import com.neovisionaries.i18n.CountryCode;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.URL;

public class GeoAPI {
	
	@NotNull
	public static GeoLocation getGeoLocation(@NotNull Inet4Address address) throws IOException {
		String ip = String.format("%d.%d.%d.%d", address.getAddress()[0], address.getAddress()[1],
				address.getAddress()[2], address.getAddress()[3]);
		HttpURLConnection urlcon = (HttpURLConnection) new URL("http://ip2c.org/" + ip).openConnection();
		urlcon.setDefaultUseCaches(false);
		urlcon.setUseCaches(false);
		urlcon.connect();
		InputStream is = urlcon.getInputStream();
		int c = 0;
		String s = "";
		while ((c = is.read()) != -1)
			s += (char) c;
		is.close();
		switch (s.charAt(0)) {
			case '0':
				throw new RuntimeException("Error");
			case '2':
				throw new RuntimeException("not found");
		}
		String[] reply = s.split(";");
		return new GeoLocation(address, CountryCode.getByCode(reply[1], false));
	}
	
}
