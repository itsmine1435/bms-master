package com.bms.configuration;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.bms.controller.BmsController;
import com.bms.module.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenGeneration {
	
	private static Logger logger = LoggerFactory.getLogger(BmsController.class);
	
	public String generateToken(User user) {
		logger.info("at gen token");
		Claims claims = Jwts.claims().setSubject(user.getUsername());
		logger.info("claims" + claims);
		claims.put("username", String.valueOf(user.getUsername()));
		claims.put("password", String.valueOf(user.getPassword()));
		logger.info("after put claims" + claims);
		String jwt = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME)).compact();
		logger.info("token created " + jwt);
		return jwt;
	}

}
