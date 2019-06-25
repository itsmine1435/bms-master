package com.bms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bms.dto.MovieDTO;
import com.bms.dto.MultiplexDTO;
import com.bms.module.Multiplex;
import com.bms.service.BmsServiceImpl;
import com.bms.service.MultiplexService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/bms/multiplex")
public class MultiplexController {
	
	@Autowired
	private MultiplexService multiplexService;
	
	private static Logger logger = LoggerFactory.getLogger(MultiplexController.class);
	
	@ApiOperation(value = "Get details of movie by selected Multiplex" , response = MovieDTO.class)
	@GetMapping("/searchMultiplex/{mid}")
	public List<MultiplexDTO> searchMultiplexByMovie(@PathVariable Long mid)
	{
		 logger.info("searchMovieByActor:{}" + mid);	
		 List<MultiplexDTO> dtos = multiplexService.searchMultiplex(mid);
		 return dtos;
	}

}
