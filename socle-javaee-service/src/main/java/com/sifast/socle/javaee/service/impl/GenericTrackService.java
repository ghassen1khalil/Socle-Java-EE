package com.sifast.socle.javaee.service.impl;

import org.springframework.stereotype.Service;

import com.sifast.socle.javaee.entities.GenericTrack;
import com.sifast.socle.javaee.service.IGenericTrackService;

@Service("genericTrackService")
public class GenericTrackService extends GenericService<GenericTrack, Integer> implements IGenericTrackService {
}