package com.lshoi.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.transaction.annotation.Transactional;

import com.lshoi.DTO.UserDTO;
import com.lshoi.models.User;
import com.lshoi.repositoty.UserDAO;
import com.lshoi.utilities.UtilityConvertBetweenEntityAndDTO;

public class UserServiceImpl implements UserService {

	private UserDAO userDAO;
	public UserServiceImpl(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	@Transactional
	@Override
	public void register(UserDTO userDTO) {
		userDTO.setPassword(DigestUtils.sha1Hex(userDTO.getPassword()));
		userDAO.register(UtilityConvertBetweenEntityAndDTO.convertToUserEntity(userDTO));
	}
	
	@Transactional
	@Override
	public List<Object> login(UserDTO userDTO) {
		userDTO.setPassword(DigestUtils.sha1Hex(userDTO.getPassword()));
		User loginUser = userDAO.login(UtilityConvertBetweenEntityAndDTO.convertToUserEntity(userDTO));
		if(loginUser != null) {
			List<Object> list = new ArrayList<>();
			UserDTO loginUserDTO = UtilityConvertBetweenEntityAndDTO.convertToUserDTO(loginUser);
			String token = AuthenticationService.getInstance().initToken(loginUserDTO);
			list.add(loginUserDTO);
			list.add(token);
			return list;
		}
		return null;
	}
	@Override
	public boolean logout(String token) {
		if(AuthenticationService.getInstance().releaseToken(token) != null)
			return true;
		return false;
	}
	
	@Transactional
	@Override
	public UserDTO getUserById(int userId) {
		return UtilityConvertBetweenEntityAndDTO.convertToUserDTO(userDAO.getUserDTO(userId));
	}
	
	@Transactional
	@Override
	public Boolean changeUserInfo(UserDTO userDTO) {
		UserDTO userInfoChange = getUserById(userDTO.getUserId());
		userDTO.setLoginName(userInfoChange.getLoginName());
		userDTO.setPassword(userInfoChange.getPassword());
		userDTO.setRole(userInfoChange.getRole());
		return userDAO.changeUserInfo(UtilityConvertBetweenEntityAndDTO.convertToUserEntity(userDTO));
	}
}
