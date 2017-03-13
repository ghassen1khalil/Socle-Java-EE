package com.sifast.socle.javaee.dao.impl;

import org.springframework.stereotype.Repository;

import com.sifast.socle.javaee.dao.IGenericTrackDao;
import com.sifast.socle.javaee.entities.GenericTrack;

@Repository("GenericTrackDao")
public class GenericTrackDao extends GenericDao<GenericTrack, Integer> implements IGenericTrackDao {
}