package com.bms.database;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.bms.module.Movie;
import com.bms.module.Multiplex;

@Repository
public class MultiplexDAOImpl extends GenericDAOImpl<Multiplex, Long> implements MultiplexDAO {

	@Override
	public List<Movie> getMovies(Long mid) {
		String hql = "from movie m where m.multiplex.id =: mid";
		Query query = entityManager.createQuery(hql);
		query.setParameter("mid", mid);
		return query.getResultList();
	}

	@Override
	public List<Multiplex> searchMultiplex(Long mid) {
		String hql = "from multiplex m where m.movies.id =: mid";
		Query query = entityManager.createQuery(hql);
		query.setParameter("mid", mid);
		return query.getResultList();
	}

}
