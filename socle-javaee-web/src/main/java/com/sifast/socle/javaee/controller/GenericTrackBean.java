package com.sifast.socle.javaee.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sifast.socle.javaee.entities.GenericTrack;
import com.sifast.socle.javaee.service.IGenericTrackService;

@Controller("genericTrackBean")
@Scope("request")
public class GenericTrackBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private transient IGenericTrackService genericTrackService;

	private List<GenericTrack> genericTrackList;

	private List<GenericTrack> filteredGenericTrackList;

	public List<GenericTrack> getGenericTrackList() {
		return genericTrackList;
	}

	public void setGenericTrackList(List<GenericTrack> genericTrackList) {
		this.genericTrackList = genericTrackList;
	}

	public List<GenericTrack> getFilteredGenericTrackList() {
		return filteredGenericTrackList;
	}

	public void setFilteredGenericTrackList(List<GenericTrack> filteredGenericTrackList) {
		this.filteredGenericTrackList = filteredGenericTrackList;
	}

	@PostConstruct
	public void init() {
		genericTrackList = genericTrackService.findAll(GenericTrack.class);
	}
}
