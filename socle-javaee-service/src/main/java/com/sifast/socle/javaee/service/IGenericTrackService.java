package com.sifast.socle.javaee.service;

import org.springframework.transaction.annotation.Transactional;

import com.sifast.socle.javaee.entities.GenericTrack;

@Transactional
public interface IGenericTrackService extends IGenericService<GenericTrack, Integer> {
}