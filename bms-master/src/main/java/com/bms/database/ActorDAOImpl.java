package com.bms.database;

import org.springframework.stereotype.Repository;

import com.bms.module.Actor;

@Repository
public class ActorDAOImpl extends GenericDAOImpl<Actor, Long> implements ActorDAO {

}
