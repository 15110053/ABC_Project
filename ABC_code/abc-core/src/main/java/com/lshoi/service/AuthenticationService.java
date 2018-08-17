package com.lshoi.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;

import com.lshoi.DTO.RoleDTO;
import com.lshoi.DTO.UserDTO;

public class AuthenticationService {
	private static AuthenticationService instance = new AuthenticationService();
	
	private AuthenticationService() {}
	
	public static AuthenticationService getInstance() {
		return instance;
	}
	private Map<String, UserDTO> tokenList = new HashMap<String, UserDTO>();
	
	public UserDTO checkTokenExist(String token) {
		return tokenList.get(token);
	}
	
	public boolean checkIsProducer(String token) {
		UserDTO u = checkTokenExist(token);
		if(u!= null && u.getRole().equals(RoleDTO.PRODUCER))
			return true;
		return false;
	}
	
	public boolean checkIsConsumer(String token) {
		UserDTO u = checkTokenExist(token);
		if(u!= null && u.getRole().equals(RoleDTO.CONSUMER))
			return true;
		return false;
	}
	
	public String initToken(UserDTO userDTO) {
		LocalDate date = LocalDate.now();
		String info = userDTO.getLoginName()+userDTO.getUserId()+date;
		String token = DigestUtils.sha1Hex(info);
		tokenList.put(token, userDTO);
		
		return token;
	}
	
	public Object releaseToken(String token) {
		return tokenList.remove(token);
	}
}
