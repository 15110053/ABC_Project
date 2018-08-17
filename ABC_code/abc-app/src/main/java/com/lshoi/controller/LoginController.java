package com.lshoi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lshoi.DTO.ResponseDTO;
import com.lshoi.DTO.UserDTO;
import com.lshoi.service.UserService;

@RestController
public class LoginController {

	private UserService userService;
	public LoginController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("Login")
	@CrossOrigin
	public ResponseDTO login(@RequestBody UserDTO userDTO) {
		Object loginToken = userService.login(userDTO);
		ResponseDTO res = new ResponseDTO();
		if (loginToken != null) {
			res.setStatus(0);
			res.setMessage("Login successful");
			res.setObject(loginToken);
			return res;
		}
		res.setStatus(1);
		res.setMessage("Login failed");
		res.setObject(loginToken);
		return res;
	}
	

	@PostMapping("Register")
	@CrossOrigin
	public ResponseDTO register(@RequestBody UserDTO userDTO) {
		try {
			System.out.println(userDTO.getUserName());
			userService.register(userDTO);
			ResponseDTO res = new ResponseDTO();
			res.setStatus(0);
			res.setMessage("Register successful");
			return res;
		}catch(Exception ex) {
			ex.printStackTrace();
			ResponseDTO res = new ResponseDTO();
			res.setStatus(1);
			res.setMessage("Register failed");
			return res;
		}
	}

}
