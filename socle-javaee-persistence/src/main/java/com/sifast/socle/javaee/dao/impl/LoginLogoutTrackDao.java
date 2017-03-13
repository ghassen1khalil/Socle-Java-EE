package com.sifast.socle.javaee.dao.impl;

import org.springframework.stereotype.Repository;

import com.sifast.socle.javaee.dao.ILoginLogoutTrackDao;
import com.sifast.socle.javaee.entities.LoginLogoutTrack;

@Repository("loginLogoutTrackDao")
public class LoginLogoutTrackDao extends GenericDao<LoginLogoutTrack, Integer> implements ILoginLogoutTrackDao {
}