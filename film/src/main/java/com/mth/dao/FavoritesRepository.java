package com.mth.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mth.model.Favorites;
import com.mth.model.Movie;
import com.mth.model.User;

public interface FavoritesRepository extends JpaRepository<Favorites, Long> {
	boolean existsByUserAndMovie(User user, Movie movie);

}