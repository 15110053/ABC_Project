package com.lshoi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lshoi.DTO.ResponseDTO;
import com.lshoi.DTO.UserDTO;
import com.lshoi.service.UserServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("User")
public class UserController {

	private UserServiceImpl userService;
	
	public UserController(UserServiceImpl userService) {
		this.userService = userService;
	}
	
	@PostMapping("Logout")
	@CrossOrigin
	public ResponseDTO logout(@RequestParam String token) {
		ResponseDTO resSuccess = new ResponseDTO();
		resSuccess.setStatus(0);
		resSuccess.setMessage("Logout successful");
		ResponseDTO resFail = new ResponseDTO();
		resFail.setStatus(1);
		resFail.setMessage("Logout failed");
		try {
			if(userService.logout(token)) return resSuccess;
			else return resFail;
		}catch(Exception ex){
			return resFail;
		}
	}
	
	@GetMapping("GetUser")
	@CrossOrigin
	public ResponseDTO getUser(@RequestParam int userId) {
		UserDTO userDTO = userService.getUserById(userId);
		if(userDTO != null) {
			ResponseDTO success = new ResponseDTO();
			success.setStatus(0);
			success.setMessage("Successfully get user");
			success.setObject(userDTO);
			return success;
		}
		ResponseDTO error = new ResponseDTO();
		error.setMessage("Fail to get user");
		error.setStatus(1);
		return error;
	}
	
	@PostMapping("UpdateUserInfo")
	@CrossOrigin
	public ResponseDTO changeUserInfo(@RequestBody UserDTO userDTO) {
		if(userService.changeUserInfo(userDTO)) {
			ResponseDTO res = new ResponseDTO();
			res.setStatus(0);
			res.setMessage("change user info success");
			return res;
		}
		ResponseDTO error = new ResponseDTO();
		error.setStatus(1);
		error.setMessage("change user info fail");
		return error;
	}
}