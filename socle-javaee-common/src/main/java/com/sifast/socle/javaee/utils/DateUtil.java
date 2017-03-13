package com.sifast.socle.javaee.utils;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public final class DateUtil {

	private DateUtil() {
		super();
	}

	public static Date calculateExpirationDate(final int expirationTimeInMinutes) {
		final Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(Instant.now().toEpochMilli());
		cal.add(Calendar.MINUTE, expirationTimeInMinutes);
		return new Date(cal.getTime().getTime());
	}
}
