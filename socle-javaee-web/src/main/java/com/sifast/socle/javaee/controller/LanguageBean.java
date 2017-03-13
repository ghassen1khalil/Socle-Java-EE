package com.sifast.socle.javaee.controller;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

@ManagedBean(name = "languageBean")
@SessionScoped
public class LanguageBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String localeCode = "fr";

	private static Map<String, Locale> countries;

	public Map<String, Locale> getCountriesInMap() {
		return countries;
	}

	public String getLocaleCode() {
		return localeCode;
	}

	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}

	static {
		countries = new LinkedHashMap<>();
		countries.put("Français", Locale.FRENCH);
		countries.put("English", Locale.ENGLISH);
		countries.put("العربيّة", new Locale("ar"));
	}

	public void onCountryLocaleCodeChange(ValueChangeEvent event) {

		String newLocaleValue = event.getNewValue().toString();

		// loop a map to compare the locale code
		for (Entry<String, Locale> entry : countries.entrySet()) {

			if (entry.getValue().toString().equals(newLocaleValue)) {
				localeCode = entry.getValue().toString();
				FacesContext.getCurrentInstance().getViewRoot().setLocale(entry.getValue());
			}
		}
	}
}