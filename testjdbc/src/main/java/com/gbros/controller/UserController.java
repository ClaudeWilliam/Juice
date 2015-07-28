package com.gbros.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.gbros.modle.User;
import com.gbros.tools.UserDAO;

@RestController

public class UserController {
	@RequestMapping(value = "/user")
	public User view() {

		return UserDAO.getuser();
	}
}
