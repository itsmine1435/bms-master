package com.example.bms;

import java.util.List;

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
import com.bms.service.BmsService;
import com.bms.module.Movie;

@RestController
@RequestMapping("/bms")
public class BmsController {

	@Autowired
	public BmsService bmsService;
	
	private static Logger logger = LoggerFactory.getLogger(BmsController.class);
	
	@GetMapping("/home")
	public void home()
	{
		bmsService.print();
	}
	
	@PostMapping("/addMovie")
	public void addMovie(@RequestBody MovieDTO movieDTO)
	{
		 logger.info("content{}" , movieDTO.getName());	
		 bmsService.addMovie(movieDTO);
	}
	
	@GetMapping("/allMovies")
	public List<MovieDTO> allMovies()
	{
		 logger.info("allMovies");	
		 List<MovieDTO> movieList = bmsService.getAllMovies();
		 return movieList;
	}
	
	@GetMapping("/searchByMovie/{id}")
	public MovieDTO searchByMovie(@PathVariable Long id)
	{
		 logger.info("searchByMovie:{}" + id);	
		 MovieDTO dto = bmsService.getMovie(id);
		 return dto;
	}
	
	@PutMapping("/updateMovie/{id}/{name}")
	public void updateMovie(@PathVariable Long id , @PathVariable String name)
	{
		 logger.info("id{}" , id);	
		 bmsService.updateMovie(id , name);
	}
	
}
