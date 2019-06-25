package com.bms.database;

import com.bms.module.User;

public interface UserDAO extends GenericDAO<User, Long> {

	User getUser(String username ,String password);

}
