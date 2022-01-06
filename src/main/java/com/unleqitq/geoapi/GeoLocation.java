package com.unleqitq.geoapi;

import com.neovisionaries.i18n.CountryCode;
import org.jetbrains.annotations.NotNull;

import java.net.Inet4Address;
import java.net.UnknownHostException;

public class GeoLocation {
	
	public final CountryCode countryCode;
	public final Inet4Address address;
	
	public GeoLocation(@NotNull Inet4Address address, CountryCode countryCode) {
		this.countryCode = countryCode;
		Inet4Address address0 = address;
		try {
			address0 = (Inet4Address) Inet4Address.getByAddress(address.getAddress());
		} catch (UnknownHostException e) {
		}
		this.address = address0;
	}
	
}
