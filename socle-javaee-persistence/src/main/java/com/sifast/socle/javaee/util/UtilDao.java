package com.sifast.socle.javaee.util;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

public class UtilDao {

	@Autowired()
	@Qualifier("sessionFactory")
	private LocalSessionFactoryBean localSessionFactory;

	public Session getSession() {
		return localSessionFactory.getObject().getCurrentSession();
	}

	protected List<?> getListNatifQuery(String query, Class<?> clazz) {
		return this.getSession().createSQLQuery(query).addEntity(clazz).list();
	}

	public LocalSessionFactoryBean getLocalSessionFactory() {
		return localSessionFactory;
	}

	public void setLocalSessionFactory(LocalSessionFactoryBean localSessionFactory) {
		this.localSessionFactory = localSessionFactory;
	}
}
