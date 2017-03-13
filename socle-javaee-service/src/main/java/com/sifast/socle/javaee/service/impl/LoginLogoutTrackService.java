package com.sifast.socle.javaee.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sifast.socle.javaee.dao.impl.LoginLogoutTrackDao;
import com.sifast.socle.javaee.entities.LoginLogoutTrack;
import com.sifast.socle.javaee.service.ILoginLogoutTrackService;

@Service("loginLogoutTrackService")
public class LoginLogoutTrackService extends GenericService<LoginLogoutTrack, Integer> implements ILoginLogoutTrackService {

	public static final Logger LOGGER = LoggerFactory.getLogger(LoginLogoutTrackService.class);

	@Autowired
	private LoginLogoutTrackDao loginLogoutTrackDao;

	@Override
	public void generateReport() {
		try {
			JasperDesign jasperDesign = JRXmlLoader.load(getClass().getClassLoader().getResourceAsStream("LoginLogoutTrackReport.jrxml"));
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			ResourceBundle resourceBundle = ResourceBundle.getBundle("message", FacesContext.getCurrentInstance().getViewRoot().getLocale());
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("userFirstName", resourceBundle.getString("FirstName"));
			parameters.put("userLastName", resourceBundle.getString("LastName"));
			parameters.put("logonDate", resourceBundle.getString("LogonDate"));
			parameters.put("logoutDate", resourceBundle.getString("LogoutDate"));
			parameters.put("ipAddress", resourceBundle.getString("IpAddress"));
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(loginLogoutTrackDao.findAll(LoginLogoutTrack.class)));
			JasperViewer.viewReport(jasperPrint, false);
			LOGGER.debug("generate report : " + resourceBundle.getString("Logging"));
		} catch (JRException e) {
			LOGGER.error("JRException " + e);
		}
	}
}