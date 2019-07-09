package com.bms.database;

import java.util.List;

import com.bms.module.Address;
import com.bms.module.Movie;
import com.bms.module.Multiplex;

public interface MultiplexDAO extends GenericDAO<Multiplex, Long> {

	public List<Movie> getMovies(Long mid);

	public List<Multiplex> searchMultiplex(Long mid);

	public List<Address> findByAddress(Long aid);
}
