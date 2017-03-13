package com.sifast.socle.javaee.service;

import org.springframework.transaction.annotation.Transactional;

import com.sifast.socle.javaee.entities.LoginLogoutTrack;

@Transactional
public interface ILoginLogoutTrackService extends IGenericService<LoginLogoutTrack, Integer> {

    void generateReport();
}