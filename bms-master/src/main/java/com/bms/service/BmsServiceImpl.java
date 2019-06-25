package com.bms.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bms.database.ActorDAO;
import com.bms.database.MovieDAO;
import com.bms.database.MultiplexDAO;
import com.bms.dto.ActorDTO;
import com.bms.dto.MovieDTO;
import com.bms.module.Actor;
import com.bms.module.Movie;
import com.bms.module.Multiplex;

@Service
@Transactional
public class BmsServiceImpl implements BmsService {

	@Autowired
	MovieDAO movieDAO;
	
	@Autowired
	MultiplexDAO multiplexDAO;
	
	@Autowired
	ActorDAO actorDAO;
	
	private static Logger logger = LoggerFactory.getLogger(BmsServiceImpl.class);

	@Override
	public void print() {
		System.out.println("***home****");
	}

	@Override
	public void addMovie(MovieDTO movieDTO) {
		Movie movie = new Movie();
		if (movieDTO != null) {
			movie.setName(Optional.ofNullable(movieDTO.getName()).orElse(""));
			Set<Long> multiplexIds = movieDTO.getMultiplexId();
			Set<Long> actorIds = movieDTO.getActorIds();
			if(actorIds.isEmpty() == false)
			{
				 Set<Actor> actors = new HashSet<Actor>();
				 actorIds.forEach(aId->{
					 actors.add(Optional.ofNullable(actorDAO.findOne(aId)).orElse(null));
				 });
				 movie.setActors(actors);
			}
			if(multiplexIds.isEmpty() == false)
			{
				 Set<Multiplex> multiplex = new HashSet<Multiplex>();
				 multiplexIds.forEach(mId->{
					 multiplex.add(Optional.ofNullable(multiplexDAO.findOne(mId)).orElse(null));
				 });
				 movie.setMultiplex(multiplex);
			}
			movieDAO.save(movie);
		}
	}

	@Override
	public List<MovieDTO> getAllMovies() {
		logger.info("Fetching all movies from system");
		List<Movie> movieList = movieDAO.findAll();
		List<MovieDTO> movieDTOS = new ArrayList<>();

		if (movieList != null) {
			movieList.forEach(m -> {
				MovieDTO dto = createMovieDTO(m);
				movieDTOS.add(Optional.ofNullable(dto).orElse(null));
			});
			return movieDTOS;
		}
		return null;

	}

	@Override
	public void updateMovie(Long id, String name) {
		Movie movie = movieDAO.findOne(id);
		if (movie != null) {
			movie.setName((Optional.ofNullable(name)).orElse(""));
			movieDAO.saveOrUpdate(movie);
		}
	}

	@Override
	public MovieDTO getMovie(Long id) {
		Movie movie = movieDAO.findOne(id);
		if(movie != null)
		{
			MovieDTO dto = createMovieDTO(movie);
			return dto ;
		}
		return null;
	}

	@Override
	public List<MovieDTO> searchMoviesByActor(Long aid) {
		Actor actor = actorDAO.findOne(aid);
		if(actor != null)
		{
			Set<Movie> movieList = actor.getMovies();
			if(!movieList.isEmpty())
			{
				List<MovieDTO> dtos = new ArrayList<MovieDTO>();
				movieList.forEach(m->{
					MovieDTO dto = createMovieDTO(m);
					dtos.add((Optional.ofNullable(dto)).orElse(null));
				});
				return dtos;
			}
		}
		return null;
	}
	
	public MovieDTO createMovieDTO(Movie movie)
	{
		MovieDTO dto = new MovieDTO();
		dto.setName(Optional.ofNullable(movie.getName()).orElse(""));
		Set<Actor> actors = movie.getActors();
		Set<String> actorsList = new HashSet<>();
		if(actors.isEmpty() == false)
		{
		actors.forEach(a->{
			actorsList.add(Optional.ofNullable(a.getName()).orElse(""));
		});
		dto.setActors(actorsList);
		}
		Set<Multiplex> multiplex = movie.getMultiplex();
		if(multiplex != null)
		{
			Set<String> multiplexList = new HashSet<>();
			multiplex.forEach(mx->{
				multiplexList.add(Optional.ofNullable(mx.getName()).orElse(""));
			});
			dto.setMultiplexes(multiplexList);
		}
		return dto ;
	}
	
	@Override
	public List<MovieDTO> searchMoviesByMultiplex(Long mid) {
		Multiplex multiplex = multiplexDAO.findOne(mid);
		if (multiplex != null) {
			Set<Movie> movies = multiplex.getMovies();
			if(!movies.isEmpty())
			{
				List<MovieDTO> dtos = new ArrayList<MovieDTO>();
				movies.forEach(m->{
					MovieDTO dto = createMovieDTO(m);
					dtos.add((Optional.ofNullable(dto)).orElse(null));
				});
				return dtos;
			}
		}
		return null;
	}		
}


