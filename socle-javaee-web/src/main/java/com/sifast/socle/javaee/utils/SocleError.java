package com.sifast.socle.javaee.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public final class SocleError {

	private SocleError() {
	}

	public static void showWarn(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, MessageUtils.getMessage("Warn"), message));
	}

	public static void showError(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, MessageUtils.getMessage("Error"), message));
	}

	public static void showInfo(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, MessageUtils.getMessage("Info"), message));
	}
}
