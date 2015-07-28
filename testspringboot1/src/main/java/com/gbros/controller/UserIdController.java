package com.gbros.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gbros.modle.User;

@Controller
@RequestMapping(value = "/qjq")
public class UserIdController {

	@RequestMapping(value = "/{id}/{name}")
	@ResponseBody
	public String view(@PathVariable("id") long id,
			@PathVariable("name") String name) {
		User user = new User();
		user.setId(id);
		user.setName(name);
		return user.toString();
	}

}
