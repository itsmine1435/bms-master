package com.bms.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bms.dto.MovieDTO;
import com.bms.dto.RegisterDTO;
import com.bms.dto.UserDTO;
import com.bms.service.BmsService;
import com.bms.service.UserService;

import io.swagger.annotations.ApiOperation;

import com.bms.module.Movie;
import com.bms.module.User;

@RestController
@RequestMapping("/bms")
public class BmsController {

	@Autowired
	public BmsService bmsService;

	@Autowired
	public UserService userService;
	
	private static Logger logger = LoggerFactory.getLogger(BmsController.class);
	
	@GetMapping("/home")
	public void home()
	{
		bmsService.print();
	}
	
	@ApiOperation(value = "Register a new user to system")
	@PostMapping("/registerUser")
	public void registerUser(@RequestBody RegisterDTO registerDTO)
	{
		 logger.info("content{}" , registerDTO.getFullName());	
		 userService.registerUser(registerDTO);
	}
	
	
	@ApiOperation(value = "Create a new movie")
	@PostMapping("/addMovie")
	public void addMovie(@RequestBody MovieDTO movieDTO)
	{
		 logger.info("content{}" , movieDTO.getName());	
		 bmsService.addMovie(movieDTO);
	}
	
	@ApiOperation(value = "Get list of all movies" , response = Iterable.class)
	@GetMapping("/allMovies")
	public List<MovieDTO> allMovies()
	{
		 logger.info("allMovies");	
		 List<MovieDTO> movieList = bmsService.getAllMovies();
		 return movieList;
	}
	
	@ApiOperation(value = "Get details of movie by selected id" , response = MovieDTO.class)
	@GetMapping("/searchByMovie/{id}")
	public MovieDTO searchByMovie(@PathVariable Long id)
	{
		 logger.info("searchByMovie:{}" + id);	
		 MovieDTO dto = bmsService.getMovie(id);
		 return dto;
	}
	
	@ApiOperation(value = "Update details of movie")
	@PutMapping("/updateMovie/{id}/{name}")
	public void updateMovie(@PathVariable Long id , @PathVariable String name)
	{
		 logger.info("id{}" , id);	
		 bmsService.updateMovie(id , name);
	}
	
	@ApiOperation(value = "Get details of movie by selected actor" , response = MovieDTO.class)
	@GetMapping("/searchMovieByActor/{aid}")
	public List<MovieDTO> searchMovieByActor(@PathVariable Long aid)
	{
		 logger.info("searchMovieByActor:{}" + aid);	
		 List<MovieDTO> dtos = bmsService.searchMoviesByActor(aid);
		 return dtos;
	}
	
	@ApiOperation(value = "Get details of movie by selected Multiplex" , response = MovieDTO.class)
	@GetMapping("/searchMovieByMultiplex/{mid}")
	public List<MovieDTO> searchMovieByMultiplex(@PathVariable Long mid)
	{
		 logger.info("searchMovieByActor:{}" + mid);	
		 List<MovieDTO> dtos = bmsService.searchMoviesByMultiplex(mid);
		 return dtos;
	}
	
	@ApiOperation(value = "Login to system" , response = Iterable.class)
	@PostMapping("/login")
	public List<MovieDTO> login(@RequestBody UserDTO userDTO , HttpServletResponse response)
	{
		 logger.info("login to system");
		 User user = userService.getUser(userDTO,response);
		 List<MovieDTO> movieList = bmsService.getAllMovies();
		 logger.info("Fetching all movies from system{}");
		 return movieList;
	}
	
	@ApiOperation(value = "Login to system" , response = Iterable.class)
	@GetMapping("/getUser/{uid}")
	public UserDTO getUser(@PathVariable Long uid)
	{
		 logger.info("Fetching user from system");
		 UserDTO user = userService.getUserById(uid);
		 return user;
	}
}
