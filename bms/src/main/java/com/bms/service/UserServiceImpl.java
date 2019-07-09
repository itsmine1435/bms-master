package com.bms.service;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bms.configuration.TokenGeneration;
import com.bms.database.UserDAO;
import com.bms.dto.RegisterDTO;
import com.bms.dto.UserDTO;
import com.bms.module.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	private TokenGeneration tokenGeneration;
	
	private static Logger logger = LoggerFactory.getLogger(BmsServiceImpl.class);
	
	@Override
	public User getUser(UserDTO userDTO , HttpServletResponse response)
	{
		logger.info("API getUser");
		String username = userDTO.getUserName();
		String password = userDTO.getPassword();
		
		User user = userDAO.getUser(username,password);
		String tokenExp = tokenGeneration.generateToken(user);
		response.addHeader("accesstoken", tokenExp);
		logger.info("tokenExp:{}",tokenExp);
		return user;
		
	}

	@Override
	public UserDTO getUserById(Long uid) {
		User user = userDAO.findOne(uid);
		UserDTO userDTO = new UserDTO();
		userDTO.setUserName(Optional.ofNullable(user.getUsername()).orElse(""));
		userDTO.setPassword(Optional.ofNullable(user.getPassword()).orElse(""));
		return userDTO;
	}

	@Override
	public void registerUser(RegisterDTO registerDTO) {
		// TODO Auto-generated method stub
		
	}

}
