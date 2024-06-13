package com.mth.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mth.model.Actor;

@Service
public interface ActorService {
	List<Actor> getActorsForMovie(Integer movie_id);
	
	public Actor getReferenceById(Integer id);

}
