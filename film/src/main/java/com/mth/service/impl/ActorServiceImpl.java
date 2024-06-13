package com.mth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mth.dao.ActorRepository;
import com.mth.model.Actor;
import com.mth.service.ActorService;

@Service
public class ActorServiceImpl implements ActorService {
	@Autowired
	private ActorRepository actorRepository;

	@Override
	public List<Actor> getActorsForMovie(Integer movie_id) {
		return actorRepository.getActorsForMovie(movie_id);
	}

	@Override
	public Actor getReferenceById(Integer id) {
		return actorRepository.getReferenceById(id);
	}

}
