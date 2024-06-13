package com.mth.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mth.model.Actor;

public interface ActorRepository extends JpaRepository<Actor, Integer> {

	@Query(value = "SELECT c.* FROM movie_actors mc JOIN actors c ON mc.actorId=c.id WHERE mc.movieId=:movie_id", nativeQuery = true)
	public List<Actor> getActorsForMovie(@Param("movie_id") Integer movie_id);

	@Query(value = "select * from actors where id =?1", nativeQuery = true)
	public Actor getReferenceById(Integer id);
}
