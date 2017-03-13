package com.sifast.socle.javaee.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sifast.socle.javaee.utils.MessageUtils;
import com.sifast.socle.javaee.utils.SocleError;

@Controller("emailSettingsBean")
@Scope("session")
public class EmailSettingsBean implements Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailSettingsBean.class);

	private static final long serialVersionUID = 1L;

	private transient PropertiesConfiguration propertiesConfiguration;

	private String smptHost;

	private String smptPort;

	private String smptProtocol;

	private String smptUserName;

	private String smptPassword;

	public String getSmptHost() {
		return smptHost;
	}

	public void setSmptHost(String smptHost) {
		this.smptHost = smptHost;
	}

	public String getSmptPort() {
		return smptPort;
	}

	public void setSmptPort(String smptPort) {
		this.smptPort = smptPort;
	}

	public String getSmptProtocol() {
		return smptProtocol;
	}

	public void setSmptProtocol(String smptProtocol) {
		this.smptProtocol = smptProtocol;
	}

	public String getSmptUserName() {
		return smptUserName;
	}

	public void setSmptUserName(String smptUserName) {
		this.smptUserName = smptUserName;
	}

	public String getSmptPassword() {
		return smptPassword;
	}

	public void setSmptPassword(String smptPassword) {
		this.smptPassword = smptPassword;
	}

	@PostConstruct
	public void init() throws ConfigurationException {
		propertiesConfiguration = new PropertiesConfiguration("email_config.properties");
		propertiesConfiguration.setReloadingStrategy(new FileChangedReloadingStrategy());
		smptHost = (String) propertiesConfiguration.getProperty("smtp.host");
		smptPort = (String) propertiesConfiguration.getProperty("smtp.port");
		smptProtocol = (String) propertiesConfiguration.getProperty("smtp.protocol");
		smptUserName = (String) propertiesConfiguration.getProperty("smtp.username");
		smptPassword = (String) propertiesConfiguration.getProperty("smtp.password");
	}

	public void saveEmailSettings() throws ConfigurationException {
		propertiesConfiguration.setProperty("smtp.host", smptHost);
		propertiesConfiguration.setProperty("smtp.port", smptPort);
		propertiesConfiguration.setProperty("smtp.protocol", smptProtocol);
		propertiesConfiguration.setProperty("smtp.username", smptUserName);
		propertiesConfiguration.setProperty("smtp.password", smptPassword);
		propertiesConfiguration.save();
		SocleError.showInfo(MessageUtils.getMessage("ConfigChangeSuccess"));
		LOGGER.debug("Email settings has been changed " + toString());
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Email Settings [propertiesConfiguration=").append(propertiesConfiguration).append(", smptHost=").append(smptHost).append(", smptPort=").append(smptPort)
				.append(", smptProtocol=").append(smptProtocol).append(", smptUserName=").append(smptUserName).append(", smptPassword=").append(smptPassword).append("]");
		return builder.toString();
	}

}