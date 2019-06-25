package com.bms.service;

import java.util.List;

import com.bms.dto.MultiplexDTO;
import com.bms.module.Multiplex;

public interface MultiplexService {

	List<MultiplexDTO> searchMultiplex(Long mid);

}
