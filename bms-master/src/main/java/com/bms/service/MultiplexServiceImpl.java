package com.bms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bms.controller.MultiplexController;
import com.bms.database.MovieDAO;
import com.bms.database.MultiplexDAO;
import com.bms.dto.MultiplexDTO;
import com.bms.module.Movie;
import com.bms.module.Multiplex;

@Service
@Transactional
public class MultiplexServiceImpl implements MultiplexService {

	@Autowired
	private MultiplexDAO multiplexDAO;
	
	@Autowired
	MovieDAO movieDAO;
	
	private static Logger logger = LoggerFactory.getLogger(MultiplexServiceImpl.class);
	
	@Override
	public List<MultiplexDTO> searchMultiplex(Long mid) {

		List<MultiplexDTO> dtos = new ArrayList<MultiplexDTO>();
		Movie movie = movieDAO.findOne(mid);
		if(movie != null)
		{
			Set<Multiplex> multiplexes = movie.getMultiplex();
			if(!multiplexes.isEmpty())
			{
				multiplexes.forEach(m->{
					MultiplexDTO dto = new MultiplexDTO();
					dto.setName((Optional.ofNullable(m.getName())).orElse(""));
					dto.setId((Optional.ofNullable(m.getId())).orElse(null));
					dtos.add(dto);
				});
				return dtos;
			}
			
		}
		//List<Multiplex> multiplexes = multiplexDAO.searchMultiplex(mid);
		
		return null;
	}
	
	

}
