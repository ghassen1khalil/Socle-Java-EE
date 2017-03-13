package com.sifast.socle.javaee.utils;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(MessageUtils.class);

	private MessageUtils() {
		super();
	}

	public static String getMessage(String key) {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			Locale locale = context.getViewRoot().getLocale();
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			ResourceBundle bundle = ResourceBundle.getBundle("message", locale, loader);
			return bundle.getString(key);
		} catch (Exception e) {
			LOGGER.error("getMessage" + e);
			return key;
		}
	}
}
