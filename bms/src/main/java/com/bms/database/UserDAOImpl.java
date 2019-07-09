package com.bms.database;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.bms.module.User;

@Repository
public class UserDAOImpl extends GenericDAOImpl<User, Long> implements UserDAO {

	@Override
	public User getUser(String username ,String password) {
		String hql = "from User u where u.username =:username and u.password =:password";
		Query query = entityManager.createQuery(hql);
		query.setParameter("username", username);
		query.setParameter("password", password);
		List results = query.getResultList();
		if (results.isEmpty())
			return null;
		else
			return (User) results.get(0);
	}

}
