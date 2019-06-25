package com.bms.service;

import java.util.List;

import com.bms.dto.MovieDTO;
import com.bms.module.Movie;


public interface BmsService {
	
	public void print();

	public void addMovie(MovieDTO movieDTO);

	public List<MovieDTO> getAllMovies();

	public void updateMovie(Long id , String name);

	public MovieDTO getMovie(Long id);

	public List<MovieDTO> searchMoviesByActor(Long aid);

	public List<MovieDTO> searchMoviesByMultiplex(Long mid);

}
