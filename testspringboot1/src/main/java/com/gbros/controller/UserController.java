package com.gbros.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gbros.modle.User;

/*
 * @RestController
 * 表明他是遵循rest形式的controller，一般返回json格式的数据
 */
@RestController
// 表明他是controller，用于对请求的控制
@RequestMapping(value = "/user")
// 设置url地址
public class UserController {

	@RequestMapping("/{id}/{name}")
	// 对http://localhost:8080/user/{id}这种形式的url进行控制
	// 这里的{id}表明id这个值是个变量
	public User view(@PathVariable("id") Long id,
			@PathVariable("name") String name) {
		// @PathVariable("id") 这个注解就是用来取url中id的值
		User user = new User();
		user.setId(id);
		user.setName(name);
		return user;

	}
}
