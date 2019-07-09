package com.bms.service;

import javax.servlet.http.HttpServletResponse;

import com.bms.dto.RegisterDTO;
import com.bms.dto.UserDTO;
import com.bms.module.User;

public interface UserService{

	User getUser(UserDTO userDTO , HttpServletResponse response);

	UserDTO getUserById(Long uid);

	void registerUser(RegisterDTO registerDTO);
	
}
