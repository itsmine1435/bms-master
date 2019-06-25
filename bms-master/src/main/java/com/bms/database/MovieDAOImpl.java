package com.bms.database;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.bms.module.Movie;

@Repository
public class MovieDAOImpl extends GenericDAOImpl<Movie, Long> implements MovieDAO {

	@Override
	public List<Movie> searchMoviesByActor(Long aid) {
		// TODO Auto-generated method stub
		return null;
	}

}
