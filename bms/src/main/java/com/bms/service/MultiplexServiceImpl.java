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
import com.bms.module.Address;
import com.bms.module.Movie;
import com.bms.module.Multiplex;

@Service
@Transactional
public class MultiplexServiceImpl implements MultiplexService {

	@Autowired
	private MultiplexDAO multiplexDAO;
	
	@Autowired
	private MovieDAO movieDAO;
	
	private static Logger logger = LoggerFactory.getLogger(MultiplexServiceImpl.class);
	
	@Override
	public MultiplexDTO searchMultiplex(Long mid) {

		Multiplex multiplex = multiplexDAO.findOne(mid);
		MultiplexDTO dto = new MultiplexDTO();
		if (multiplex != null) {
			Address address = multiplex.getAddress();
			dto.setArea(Optional.ofNullable(address).map(a -> a.getArea()).orElse("unavailable"));
			dto.setName(Optional.ofNullable(multiplex.getName()).orElse(""));
			dto.setId(Optional.ofNullable(multiplex.getId()).orElse(null));
			return dto;

		}
		//List<Multiplex> multiplexes = multiplexDAO.searchMultiplex(mid);
		logger.info("multiplex details:{}",dto.toString());
		return null;
	}
	
	

}
